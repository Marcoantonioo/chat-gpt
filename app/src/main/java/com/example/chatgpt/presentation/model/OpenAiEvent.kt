package com.example.chatgpt.presentation.model

sealed class OpenAiEvent {
    data class MakeQuestion(val message: String) : OpenAiEvent()
}