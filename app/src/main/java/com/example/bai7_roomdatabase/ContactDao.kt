package com.example.bai7_roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("Select * From Contact")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("Select * From Contact Where Id= :contactID")
    fun getContactById(contactID: Int): Flow<Contact>

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}