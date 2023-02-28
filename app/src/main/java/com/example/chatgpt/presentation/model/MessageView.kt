package com.example.chatgpt.presentation.model

data class MessageView(
    val message: List<String>,
    val isFromMe: Boolean,
    val createdAt: Long
)
