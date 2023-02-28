package com.example.chatgpt.features.chat.presentation.model

sealed class OpenAiEvent {
    data class MakeQuestion(val message: String) : OpenAiEvent()
}