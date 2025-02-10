package com.example.chat_simple

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

sealed class NavRoute(val route:String){
    object WELCOME:NavRoute("wellcome_screen")
    object LOGIN:NavRoute("login_screen")
    object REGISTER:NavRoute("register_screen")
    object HOME:NavRoute("contactlist_screen")
    object CHAT:NavRoute("chat_screen")
}

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(
        navController=navController,
        startDestination = if(Firebase.auth.currentUser!=null)
        NavRoute.HOME.route
        else NavRoute.WELCOME.route
    ) {
        composable(NavRoute.WELCOME.route){
            WelcomeScreen(navController)
        }
        composable(NavRoute.REGISTER.route){
            RegisterScreen(navController)
        }
        composable(NavRoute.LOGIN.route){
            LoginScreen(navController)
        }
        composable(NavRoute.HOME.route){
            ContactListScreen(navController)
        }
        composable(
            NavRoute.CHAT.route+"?email={email}",
            arguments = listOf(navArgument("email"){nullable=false})
        ){
            var email=it.arguments?.getString("email")
            ChatScreen(navController=navController,email?:"")
        }
    }
}