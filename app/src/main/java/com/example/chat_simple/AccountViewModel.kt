package com.example.chat_simple

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class AccountViewModel:ViewModel(){
    var state by mutableStateOf(AccountState())
        private set

    fun addUser(){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(state.email,state.password)
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    state=state.copy(succes=false)
                }else{
                    val user=User(
                        state.email,
                        state.password,
                        state.fullname
                    )
                    Firebase.firestore.collection("users").add(user)
                    state=state.copy(succes = true)
                }
            }
    }

    fun SignIn(){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(state.email,state.password)
            .addOnCompleteListener {
                state=state.copy(succes=it.isSuccessful)
            }
    }

    fun onChangeFullname(newvalue:String){
        state=state.copy(fullname=newvalue)
    }

    fun onChangeEmail(newvalue: String){
        state=state.copy(email=newvalue)
    }

    fun onChangePassword(newvalue: String){
        state=state.copy(password=newvalue)
    }
}

data class AccountState(
    val fullname:String="",
    val email:String="",
    val password:String="",
    val succes:Boolean=false
)