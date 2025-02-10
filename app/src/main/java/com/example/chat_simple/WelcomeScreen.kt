package com.example.chat_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Chat Simple",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 50.sp
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            onClick = {
                //(1) hiện thực chuyển hướng sang màn hình đăng nhập
                navController.navigate(NavRoute.LOGIN.route)
            },
            shape = RoundedCornerShape(10)
        ) {
            Text(
                text="Đăng Nhập",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            onClick = {
                //(2) hiện thực chuyển hướng sang màn hình đăng ký
                navController.navigate(NavRoute.REGISTER.route)
            },
            shape = RoundedCornerShape(10)
        ) {
            Text(
                text = "Đăng Ký",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}