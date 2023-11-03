package com.example.bai7_roomdatabase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai7_roomdatabase.Graph.repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactSearchViewModel(
    private val contactRepository: ContactRepository = Graph.repository
): ViewModel() {
    var state by mutableStateOf(ContactSearchState())
    init {
        viewModelScope.launch {
            contactRepository.contacts.collectLatest {
                state = state.copy(
                    contacts = it
                )
            }
        }
    }
    fun onChangeText(newvalue: String){
        viewModelScope.launch {
            state = state.copy(
                txtSearch = newvalue
            )
            if (newvalue.isEmpty()){
                repository.contacts.collectLatest {
                    state = state.copy(
                        contacts = it
                    )
                }
            }
            else {
                repository.searchContact(newvalue).collectLatest {
                    state = state.copy(
                        contacts = it
                    )
                }
            }
        }
    }
    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            contactRepository.deleteContact(contact = contact)
        }
    }
}
data class ContactSearchState(
    val txtSearch : String = "",
    val contacts: List<Contact> = emptyList()
)