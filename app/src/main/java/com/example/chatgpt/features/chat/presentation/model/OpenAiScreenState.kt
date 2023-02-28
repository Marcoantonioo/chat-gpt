package com.example.chatgpt.features.chat.presentation.model

sealed class OpenAiScreenState {

    data class Loading(val isLoading: Boolean) : OpenAiScreenState()

    data class Success(val data: MutableList<MessageView>, val isLocal: Boolean = false) :
        OpenAiScreenState()

    data class Error(val error: String) : OpenAiScreenState()

}