package com.nckim.mvvmaacstudy.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nckim.mvvmaacstudy.R
import com.nckim.mvvmaacstudy.db.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter (private val itemClick: (Contact) -> Unit,
    private val itemLongClick: (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private var contacts : List<Contact> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact){
            itemView.tv_name.text = contact.name
            itemView.tv_number.text = contact.number
            itemView.tv_initial.text = contact.initial.toString()
            itemView.setOnClickListener { itemClick(contact) }
            itemView.setOnLongClickListener {
                itemLongClick(contact)
                return@setOnLongClickListener true
            }
        }
    }

    fun setContacts(contacts : List<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }

}