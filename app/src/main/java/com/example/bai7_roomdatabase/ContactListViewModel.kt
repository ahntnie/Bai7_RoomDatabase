package com.example.bai7_roomdatabase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactRepository: ContactRepository = Graph.repository
): ViewModel() {
    var state by mutableStateOf(ContactListState())
        private set
    init {
        getAllContacs()
    }
    private fun getAllContacs(){
        viewModelScope.launch {
            contactRepository.contacts.collectLatest {
                state = state.copy(
                    contacts = it
                )
            }
        }
    }
    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            contactRepository.deleteContact(contact = contact)
        }
    }
    fun getContactById(id: Int){
        viewModelScope.launch {
            contactRepository.getContactByID(id).collectLatest {
                state = state.copy(
                    contactFound = it
                )
            }
        }
    }
}
data class ContactListState(
    val contacts: List<Contact> = emptyList(),
    val contactFound: Contact = Contact(),
)