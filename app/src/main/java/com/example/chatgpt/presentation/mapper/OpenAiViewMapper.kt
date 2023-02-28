package com.example.chatgpt.presentation.mapper

import com.example.chatgpt.domain.model.OpenAiDomain
import com.example.chatgpt.presentation.model.MessageView
import java.util.*

fun OpenAiDomain.mapToView() = MessageView(
    createdAt = created ?: 0,
    message = choices?.map { it.text ?: "" } ?: emptyList(),
    isFromMe = false
)

fun String.mapMessageToMe() = MessageView(
    createdAt = Calendar.getInstance().time.time,
    isFromMe = true,
    message = listOf(this)
)
