package com.example.utils

import com.example.chatgpt.features.chat.data.remote.entity.OpenAiChoiceResponse
import com.example.chatgpt.features.chat.data.remote.entity.OpenAiResponse

object TestUtils {
    fun getOpenAiResponse() = OpenAiResponse(
        created = 0,
        choices = listOf(OpenAiChoiceResponse(text = "Teste"))
    )
}