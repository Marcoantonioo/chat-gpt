package com.example.chatgpt.presentation.di

import com.example.chatgpt.presentation.viewmodel.OpenAiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal object PresentationModule {
    val module = module {
        viewModel { OpenAiViewModel(userCase = get()) }
    }
}