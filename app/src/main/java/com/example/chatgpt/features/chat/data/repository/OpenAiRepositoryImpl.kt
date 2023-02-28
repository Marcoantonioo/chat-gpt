package com.example.chatgpt.features.chat.data.repository

import com.example.chatgpt.commons.request.doRequest
import com.example.chatgpt.features.chat.data.mapper.mapToDomain
import com.example.chatgpt.features.chat.data.mapper.mapToRequest
import com.example.chatgpt.features.chat.data.remote.service.OpenAiApi
import com.example.chatgpt.features.chat.domain.model.OpenAiDomain
import com.example.chatgpt.features.chat.domain.repository.IOpenAiRepository

class OpenAiRepositoryImpl(
    private val api: OpenAiApi
) : IOpenAiRepository {

    override suspend fun makeQuestion(message: String): OpenAiDomain {
        return doRequest {
            api.makeQuestion(request = message.mapToRequest())
        }.mapToDomain()
    }
}
