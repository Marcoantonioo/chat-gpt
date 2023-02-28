package com.example.chatgpt

import android.app.Application
import com.example.chatgpt.data.di.DataModule
import com.example.chatgpt.domain.di.DomainModule
import com.example.chatgpt.network.di.NetworkModule
import com.example.chatgpt.presentation.di.PresentationModule
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
        PresentationModule.module,
        DomainModule.module,
        DataModule.module,
        NetworkModule.module
    )
}