package com.example.chatgpt.presentation.model

data class OpenAiView(
    val created: Long? = null,
    val choices: List<OpenAiChoiceView>? = null
)

data class OpenAiChoiceView(
    val text: String? = null,
    val isFromMe: Boolean
)
