package com.example.chatgpt.features.chat.domain.usecase

import com.example.chatgpt.features.chat.domain.model.OpenAiDomain

interface IMakeQuestionUseCase {
    suspend operator fun invoke(message: String): OpenAiDomain
}