package com.example.chatgpt.features.chat.presentation.mapper

import com.example.chatgpt.features.chat.domain.model.OpenAiDomain
import com.example.chatgpt.features.chat.presentation.model.MessageView
import java.util.*

fun OpenAiDomain.mapToView() = MessageView(
    createdAt = created ?: 0,
    message = choices?.map { it.text ?: "" } ?: emptyList(),
    isFromMe = false
)

fun String.mapMessageToMe() = MessageView(
    createdAt = null,
    isFromMe = true,
    message = listOf(this)
)
