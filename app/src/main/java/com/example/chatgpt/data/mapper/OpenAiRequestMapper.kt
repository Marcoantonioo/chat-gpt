package com.example.chatgpt.data.mapper

import com.example.chatgpt.data.remote.entity.OpenAiRequest

fun String.mapToRequest() = OpenAiRequest(this)
