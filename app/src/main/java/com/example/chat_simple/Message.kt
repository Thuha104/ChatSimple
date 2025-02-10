package com.example.chat_simple

import android.provider.Settings

data class Message(
    val sender:String="",
    val receiver:String="",
    val content:String="",
    val timeline:Long=System.currentTimeMillis()
)