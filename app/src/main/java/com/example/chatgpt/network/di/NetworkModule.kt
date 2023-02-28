package com.example.chatgpt.network.di

import com.example.chatgpt.network.RetrofitConfig
import org.koin.dsl.module

internal object NetworkModule {
    val module = module {
        single { RetrofitConfig.provideRetrofit() }
    }
}