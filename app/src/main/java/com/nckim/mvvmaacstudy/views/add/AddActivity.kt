package com.nckim.mvvmaacstudy.views.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nckim.mvvmaacstudy.R
import com.nckim.mvvmaacstudy.db.Contact
import com.nckim.mvvmaacstudy.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class AddActivity : AppCompatActivity() {
    private lateinit var viewModel : ContactViewModel
    private var id : Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        if(intent != null && intent.hasExtra(EXTRA_CONTACT_ID) && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER) ){
            et_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            et_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }


        done_button.setOnClickListener{
            val name = et_name.text.toString().trim()
            val number = et_number.text.toString().trim()
            if(!name.isEmpty() && !number.isEmpty()){
                val initial = name[0].toUpperCase()
                var contact = Contact(id, et_name.text.toString(), et_number.text.toString(), initial)
                viewModel.insert(contact)
                finish()
            }else{
                Toast.makeText(this, "이름또는 번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object{
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
    }
}