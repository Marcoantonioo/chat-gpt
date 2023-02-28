package com.example.chatgpt.features.chat.data.mapper

import com.example.chatgpt.features.chat.data.remote.entity.OpenAiRequest

fun String.mapToRequest() = OpenAiRequest(this)
