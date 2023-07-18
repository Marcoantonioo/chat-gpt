package com.example.chatgpt.features.chat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt.features.chat.domain.model.OpenAiDomain
import com.example.chatgpt.features.chat.domain.usecase.IMakeQuestionUseCase
import com.example.chatgpt.features.chat.presentation.mapper.mapMessageToMe
import com.example.chatgpt.features.chat.presentation.mapper.mapToView
import com.example.chatgpt.features.chat.presentation.model.MessageView
import com.example.chatgpt.features.chat.presentation.model.OpenAiEvent
import com.example.chatgpt.features.chat.presentation.model.OpenAiScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OpenAiViewModel(
    private val userCase: IMakeQuestionUseCase
) : ViewModel() {

    private val _state: MutableSharedFlow<OpenAiScreenState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    private val messages = mutableListOf<MessageView>()

    fun dispatchEvent(event: OpenAiEvent) {
        when (event) {
            is OpenAiEvent.MakeQuestion -> makeQuestion(message = event.message)
        }
    }

    private fun makeQuestion(message: String) {
        viewModelScope.launch {
            handleStart(message)
            runCatching {
                userCase(message)
            }.onSuccess {
                handleSuccess(it)
            }.onFailure {
                handleFailure(it)
            }
        }
    }

    private suspend fun handleStart(message: String) {
        _state.emit(OpenAiScreenState.Loading(isLoading = true))
        populateMessage(message = message.mapMessageToMe(), isLocal = true)
    }

    private suspend fun handleSuccess(item: OpenAiDomain) {
        populateMessage(message = item.mapToView())
        _state.emit(OpenAiScreenState.Loading(isLoading = false))
    }

    private suspend fun handleFailure(error: Throwable) {
        error.message?.let { message ->
            _state.run {
                populateMessage("Serviço indisponível no momento".mapMessageToMe())
                emit(OpenAiScreenState.Error(message))
                emit(OpenAiScreenState.Loading(isLoading = false))
            }
        }
    }

    private suspend fun populateMessage(message: MessageView, isLocal: Boolean = false) {
        _state.run {
            messages.run {
                add(message.apply { isFromMe = isLocal })
                emit(OpenAiScreenState.Success(data = this, isLocal = isLocal))
            }
        }
    }
}
