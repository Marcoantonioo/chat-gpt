package com.example.chatgpt.features.chat.presentation.model

data class MessageView(
    val message: List<String>,
    val isFromMe: Boolean,
    val createdAt: Long
)
