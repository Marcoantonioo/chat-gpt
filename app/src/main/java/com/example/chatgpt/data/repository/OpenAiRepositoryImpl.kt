package com.example.chatgpt.data.repository

import com.example.chatgpt.commons.request.doRequest
import com.example.chatgpt.data.mapper.mapToDomain
import com.example.chatgpt.data.mapper.mapToRequest
import com.example.chatgpt.data.remote.service.OpenAiApi
import com.example.chatgpt.domain.model.OpenAiDomain
import com.example.chatgpt.domain.repository.IOpenAiRepository

class OpenAiRepositoryImpl(
    private val api: OpenAiApi
) : IOpenAiRepository {

    override suspend fun makeQuestion(message: String): OpenAiDomain {
        return doRequest {
            api.makeQuestion(request = message.mapToRequest())
        }.mapToDomain()
    }
}
