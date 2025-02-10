package com.example.chat_simple

data class ChatState(
    val friend:User=User(),
    val message:List<Message> = emptyList(),
    val mess:String=""
)