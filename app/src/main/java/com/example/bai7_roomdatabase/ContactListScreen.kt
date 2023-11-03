package com.example.bai7_roomdatabase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController){
    var viewModel: ContactListViewModel = viewModel(
        modelClass = ContactListViewModel::class.java
    )
    var contactListState = viewModel.state
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    TextField(
                        value = "", onValueChange ={},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Search")
                        },
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Rounded.Search,
                                    contentDescription = "" )
                            }
                        },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Rounded.QrCode2,
                                        contentDescription = null
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Rounded.Settings,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.LightGray
                        ),

                    )
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = {
                 navController.navigate(NavRoute.DETAIL_SCREEN.route)
            },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add phone")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
        ){
        LazyColumn(contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)){
            items(contactListState.contacts){
                con -> con
                CardInfo(name = con.FullName, phone =con.Phone,
                    onClickCard = {
                        navController.navigate(
                            NavRoute.DETAIL_SCREEN.route+
                            "?id=${con.Id}"
                        )

                    },
                    onDeleteCard = {
                        viewModel.deleteContact(con)
                    }
                    )
            }
            }
        }
    }
