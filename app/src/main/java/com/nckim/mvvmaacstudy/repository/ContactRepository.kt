package com.nckim.mvvmaacstudy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.nckim.mvvmaacstudy.db.Contact
import com.nckim.mvvmaacstudy.db.ContactDao
import com.nckim.mvvmaacstudy.db.ContactDatabase
import java.lang.Exception

class ContactRepository(application: Application) {
    private val contactDatabase : ContactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao : ContactDao = contactDatabase.contactDao()
    private val contacts = contactDao.getAll()

    public fun getAll() : LiveData<List<Contact>>{
        return contacts
    }

    public fun insert(contact: Contact){
        try{
            val thread = Thread(Runnable {
                contactDao.insert(contact)
            })
            thread.start()
        }catch (e: Exception){

        }

    }

    public fun delete(contact: Contact){
        try{
            Thread(Runnable {
                contactDao.delete(contact)
            }).start()
        }catch (e : Exception){

        }

    }
}