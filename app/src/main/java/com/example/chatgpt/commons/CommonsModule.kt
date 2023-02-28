package com.example.chatgpt.commons

import android.content.Context
import android.content.SharedPreferences
import com.example.chatgpt.commons.sharedpreferences.ISharedPreferencesManager
import com.example.chatgpt.commons.sharedpreferences.SharedPreferencesManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal object CommonsModule {
    val module = module {

        single<SharedPreferences> {
            this.androidContext().getSharedPreferences("chat_gpt", Context.MODE_PRIVATE)
        }

        single<ISharedPreferencesManager> {
            SharedPreferencesManagerImpl(
                sharedPreferences = get()
            )
        }
    }
}