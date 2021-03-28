package com.example.template

import android.app.Application
import com.example.template.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@TemplateApplication)
            modules(listOf(networkModule))
        }
    }
}