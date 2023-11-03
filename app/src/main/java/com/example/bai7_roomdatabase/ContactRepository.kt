package com.example.bai7_roomdatabase

class ContactRepository(private val contactDao: ContactDao)
{
    val contacts = contactDao.getAllContacts()
    fun getContactByID(id: Int) = contactDao.getContactById(id)
    fun searchContact(txtSearch:String)=contactDao.searchContact(txtSearch)
    suspend fun deleteContact(contact: Contact){
        contactDao.deleteContact(contact)
    }
    suspend fun inserContact(contact: Contact){
        contactDao.insertContact(contact)
    }
    suspend fun updateContact(contact: Contact){
        contactDao.updateContact(contact)
    }

}