package com.nckim.mvvmaacstudy.views.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.nckim.mvvmaacstudy.R
import com.nckim.mvvmaacstudy.db.Contact
import com.nckim.mvvmaacstudy.repository.ContactRepository
import com.nckim.mvvmaacstudy.viewmodel.ContactViewModel
import com.nckim.mvvmaacstudy.views.add.AddActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var contactViewModel : ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactAdapter({contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            startActivity(intent)
        }, {contact ->
            deleteContact(contact)
        })

        contact_recycler.adapter = adapter
        contact_recycler.layoutManager = LinearLayoutManager(this)

        val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(contact_recycler)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer { contacts ->
            adapter.setContacts(contacts)
        })

        add_button.setOnClickListener{
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun deleteContact(contact: Contact){
        val alert = AlertDialog.Builder(this)
        alert.setMessage("Are you sure Delete?")
            .setPositiveButton("YES"){_,_->
            contactViewModel?.delete(contact)
        }.setNegativeButton("NO"){_, _ ->}
            .show()
    }
}