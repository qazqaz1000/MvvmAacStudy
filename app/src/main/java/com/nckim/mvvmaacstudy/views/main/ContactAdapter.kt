package com.nckim.mvvmaacstudy.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nckim.mvvmaacstudy.R
import com.nckim.mvvmaacstudy.databinding.ContactItemBinding
import com.nckim.mvvmaacstudy.db.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter (private val itemClick: (Contact) -> Unit,
    private val itemLongClick: (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>(), ItemTouchHelperListener {
    private var contacts : List<Contact> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        val binding : ContactItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.contact_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(private val binding : ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact){
            binding.contact = contact
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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val contact = contacts[fromPosition]
        val tempContacts = contacts.toMutableList()
        tempContacts.removeAt(fromPosition)
        tempContacts.add(toPosition, contact)
        contacts = tempContacts.toList()
        notifyItemMoved(fromPosition, toPosition)

        return true
    }

    override fun onItemSwipe(position: Int) {
        val tempContacts = contacts.toMutableList()
        tempContacts.removeAt(position)
        contacts = tempContacts.toList()
        notifyItemRemoved(position)
    }

}