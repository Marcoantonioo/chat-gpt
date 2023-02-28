package com.example.chatgpt.domain.repository

import com.example.chatgpt.domain.model.OpenAiDomain

interface IOpenAiRepository {
    suspend fun makeQuestion(message: String): OpenAiDomain
}
