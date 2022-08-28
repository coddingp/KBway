package com.example.kbway

//import android.app.Application
//import com.bus.common.di.CommonModule
//import com.bus.main.di.MainModule
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.context.startKoin
//import timber.log.Timber
//
//class App : Application() {
//
//    override fun onCreate() {
//        super.onCreate()
//        Timber.plant(Timber.DebugTree())
//        setupKoin()
//    }
//
//    private fun setupKoin() {
//        startKoin {
//            androidContext(this@App)
//            modules(
//                CommonModule.createMainRetrofit(),
//                MainModule.create()
//            )
//        }
//    }
//}