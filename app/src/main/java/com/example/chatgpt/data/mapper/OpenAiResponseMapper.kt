package com.example.chatgpt.data.mapper

import com.example.chatgpt.data.remote.entity.OpenAiChoiceResponse
import com.example.chatgpt.data.remote.entity.OpenAiResponse
import com.example.chatgpt.domain.model.OpenAiChoiceDomain
import com.example.chatgpt.domain.model.OpenAiDomain

fun OpenAiResponse.mapToDomain() = OpenAiDomain(
    created = created,
    choices = choices.mapList()
)

fun List<OpenAiChoiceResponse>?.mapList() = this?.map {
    OpenAiChoiceDomain(
        text = it.text
    )
} ?: emptyList()