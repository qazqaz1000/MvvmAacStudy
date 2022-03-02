package com.nckim.mvvmaacstudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nckim.mvvmaacstudy.db.Contact
import com.nckim.mvvmaacstudy.repository.ContactRepository

class ContactViewModel (application: Application) : AndroidViewModel(application){
    private val contactRepository = ContactRepository(application)
    private val contacts = contactRepository.getAll()

    fun getAll() : LiveData<List<Contact>>{
        return contacts
    }

    fun insert(contact: Contact){
        contactRepository.insert(contact)
    }

    fun delete(contact: Contact){
        contactRepository.delete(contact)
    }


}