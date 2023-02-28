package com.example.chatgpt.domain.usecase

import com.example.chatgpt.domain.model.OpenAiDomain

interface IMakeQuestionUseCase {
    suspend operator fun invoke(message: String): OpenAiDomain
}