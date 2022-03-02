package com.nckim.mvvmaacstudy.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ContactDao {
    @Query ("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert (onConflict = REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}