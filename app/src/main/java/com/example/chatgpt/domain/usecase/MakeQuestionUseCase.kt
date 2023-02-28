package com.example.chatgpt.domain.usecase

import com.example.chatgpt.domain.repository.IOpenAiRepository

class MakeQuestionUseCase(
    private val repository: IOpenAiRepository
) : IMakeQuestionUseCase {

    override suspend fun invoke(message: String) =
        repository.makeQuestion(message)
}