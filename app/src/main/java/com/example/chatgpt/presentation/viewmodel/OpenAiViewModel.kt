package com.example.chatgpt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt.domain.model.OpenAiDomain
import com.example.chatgpt.domain.usecase.IMakeQuestionUseCase
import com.example.chatgpt.presentation.mapper.mapMessageToMe
import com.example.chatgpt.presentation.mapper.mapToView
import com.example.chatgpt.presentation.model.MessageView
import com.example.chatgpt.presentation.model.OpenAiEvent
import com.example.chatgpt.presentation.model.OpenAiScreenState
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
        populateMessage(message = message.mapMessageToMe(), isLocal = true)
    }

    private suspend fun handleSuccess(item: OpenAiDomain) {
        populateMessage(message = item.mapToView())
    }

    private suspend fun handleFailure(error: Throwable) {
        error.message?.let { message ->
            _state.run {
                emit(OpenAiScreenState.Loading(isLoading = false))
                emit(OpenAiScreenState.Error(message))
            }
        }
    }

    private suspend fun populateMessage(message: MessageView, isLocal: Boolean = false) {
        _state.run {
            emit(OpenAiScreenState.Loading(isLoading = false))
            messages.run {
                add(message)
                emit(OpenAiScreenState.Success(data = this, isLocal = isLocal))
            }
        }
    }
}
