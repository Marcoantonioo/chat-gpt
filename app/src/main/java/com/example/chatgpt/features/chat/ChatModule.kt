package com.example.chatgpt.features.chat

import com.example.chatgpt.features.chat.data.remote.service.OpenAiApi
import com.example.chatgpt.features.chat.data.repository.OpenAiRepositoryImpl
import com.example.chatgpt.features.chat.domain.repository.IOpenAiRepository
import com.example.chatgpt.features.chat.domain.usecase.IMakeQuestionUseCase
import com.example.chatgpt.features.chat.domain.usecase.MakeQuestionUseCase
import com.example.chatgpt.features.chat.presentation.viewmodel.OpenAiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal object ChatModule {
    val module = module {
        factory<IOpenAiRepository> {
            OpenAiRepositoryImpl(
                api = get<Retrofit>().create(OpenAiApi::class.java)
            )
        }

        factory<IMakeQuestionUseCase> {
            MakeQuestionUseCase(
                repository = get()
            )
        }
        viewModel { OpenAiViewModel(userCase = get()) }
    }
}
