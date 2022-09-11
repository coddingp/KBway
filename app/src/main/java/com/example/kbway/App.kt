package com.example.kbway

import android.app.Application
import com.example.kbway.common.di.CommonModule
import com.example.kbway.userRoute.di.RouteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                CommonModule.createMainRetrofit(),
                RouteModule.create()
            )
        }
    }
}