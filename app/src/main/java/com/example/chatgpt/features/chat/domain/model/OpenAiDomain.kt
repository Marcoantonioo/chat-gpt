package com.example.chatgpt.features.chat.domain.model

data class OpenAiDomain(
    val created: Long? = null,
    val choices: List<OpenAiChoiceDomain>? = null
)

data class OpenAiChoiceDomain(
    val text: String? = null
)
