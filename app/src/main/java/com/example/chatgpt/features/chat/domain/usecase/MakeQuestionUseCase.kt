package com.example.chatgpt.features.chat.domain.usecase

import com.example.chatgpt.features.chat.domain.repository.IOpenAiRepository

class MakeQuestionUseCase(
    private val repository: IOpenAiRepository
) : IMakeQuestionUseCase {

    override suspend fun invoke(message: String) =
        repository.makeQuestion(message)
}