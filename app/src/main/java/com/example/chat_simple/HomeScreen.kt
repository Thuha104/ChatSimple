package com.example.chat_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController){
    //tạo danh sách tạm sẽ được hiện thực lại sau
    var viewModel:HomeViewModel= viewModel(
        modelClass = HomeViewModel::class.java
    )
    var state=viewModel.state
    var list= mutableListOf<User>()
    list.add(User("abc@gmail.com","123456","Nguyen Le"))
    list.add(User("xyz@gmail.com","123456","Tien Lu"))
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Danh Sách")},
                actions = {
                    IconButton(
                        onClick = {
                            //(5) hiện thực đăng xuất
                            viewModel.SignOut()
                            navController.navigate(NavRoute.WELCOME.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ){
        LazyColumn (
            modifier = Modifier
                .padding(it)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            if(state.contactList.count()>=0){
                items(state.contactList){
                    CardInfo(user = it,{
                        navController.navigate(
                            NavRoute.CHAT.route+"?email=${it.email}"
                        )
                    })
                }
            }else{
                item { Text(text = "Không có danh sách") }
            }
        }
    }
}