package com.example.chatgpt.data.di

import com.example.chatgpt.data.remote.service.OpenAiApi
import com.example.chatgpt.data.repository.OpenAiRepositoryImpl
import com.example.chatgpt.domain.repository.IOpenAiRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal object DataModule {
    val module = module {

        factory<IOpenAiRepository> {
            OpenAiRepositoryImpl(
                api = get<Retrofit>().create(OpenAiApi::class.java)
            )
        }
    }
}