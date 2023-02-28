package com.example.chatgpt.domain.di

import com.example.chatgpt.domain.usecase.IMakeQuestionUseCase
import com.example.chatgpt.domain.usecase.MakeQuestionUseCase
import org.koin.dsl.module

internal object DomainModule {
    val module = module {
        factory<IMakeQuestionUseCase> {
            MakeQuestionUseCase(
                repository = get()
            )
        }
    }
}