package com.example.chatgpt

import android.app.Application
import com.example.chatgpt.commons.CommonsModule
import com.example.chatgpt.features.chat.ChatModule
import com.example.chatgpt.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            getModules()
        }
    }

    private fun KoinApplication.getModules() = modules(
        CommonsModule.module,
        NetworkModule.module,
        ChatModule.module
    )
}