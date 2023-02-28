package com.example.chatgpt.features.chat.data.mapper

import com.example.chatgpt.features.chat.data.remote.entity.OpenAiChoiceResponse
import com.example.chatgpt.features.chat.data.remote.entity.OpenAiResponse
import com.example.chatgpt.features.chat.domain.model.OpenAiChoiceDomain
import com.example.chatgpt.features.chat.domain.model.OpenAiDomain

fun OpenAiResponse.mapToDomain() = OpenAiDomain(
    created = created,
    choices = choices.mapList()
)

fun List<OpenAiChoiceResponse>?.mapList() = this?.map {
    OpenAiChoiceDomain(
        text = it.text
    )
} ?: emptyList()