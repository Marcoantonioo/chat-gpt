package com.example.chatgpt.features.chat.domain.repository

import com.example.chatgpt.features.chat.domain.model.OpenAiDomain

interface IOpenAiRepository {
    suspend fun makeQuestion(message: String): OpenAiDomain
}
