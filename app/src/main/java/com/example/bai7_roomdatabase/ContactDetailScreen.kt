package com.example.bai7_roomdatabase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(navController: NavController, id: Int = -1){
    var viewModel = viewModel<ContactDetailViewModel>(
        factory = ContactDetailViewModelFactor(id)
    )
    var contactState=viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    Text(text = if (id < 0) "Add new contact"
                        else "Update contact"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Filled.ArrowBack, 
                            contentDescription = null )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(value = contactState.fullname,
                onValueChange = viewModel::onChangeFullName,
                label = {Text(text = "Email")
                })
            OutlinedTextField(value = contactState.phone,
                onValueChange =viewModel::onChangePhone,
                label = {Text(text = "Email")
                })
            OutlinedTextField(value = contactState.email,
                onValueChange =viewModel::onChangeEmail,
                label = {Text(text = "Email")
                })
            Button(onClick = {
                 if(id>0){
                     var contact = Contact(
                         id, contactState.phone
                         ,contactState.fullname,
                         contactState.email
                     )
                     viewModel.updateContact(contact)
                 }
                else{
                    var contact = Contact(
                        Phone = contactState.phone,
                        FullName = contactState.fullname,
                        Email = contactState.email
                    )
                     viewModel.insertContact(contact)
                 }
                navController.popBackStack()
            },
                shape = RoundedCornerShape(10)
            ) {
                Text(text = "Save")
            }
        }
    }
}