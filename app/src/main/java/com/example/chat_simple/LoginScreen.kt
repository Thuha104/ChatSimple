package com.example.chat_simple

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController){
    var viewModel:AccountViewModel= viewModel(
        modelClass = AccountViewModel::class.java
    )
    var context= LocalContext.current
    var state=viewModel.state
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Đăng Nhập")},
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){
        Column (
            modifier = Modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text="Chat Simple",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp
            )
            OutlinedTextField(
                value = state.email, onValueChange = viewModel::onChangeEmail,
                placeholder = { Text(text="Email")},
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )
            OutlinedTextField(
                value = state.password, onValueChange = viewModel::onChangePassword,
                placeholder = { Text(text = "Password")},
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                onClick = {
                    //(3) Thực hiện chức năng đăng nhập
                    viewModel.SignIn()
//                    if(state.succes){
//                        navController.navigate(NavRoute.HOME.route)
//                    }
                },
                shape = RoundedCornerShape(10)
            ) {
                Text(
                    text = "Đăng Nhập",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

            }
        }
    }
    LaunchedEffect(state.succes) {
        if (state.succes) {
            Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            navController.navigate(NavRoute.HOME.route)
        } else if (!state.succes && state.email.isNotEmpty() && state.password.isNotEmpty()) {
            Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
        }
    }
}