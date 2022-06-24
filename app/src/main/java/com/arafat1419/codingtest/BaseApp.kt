package com.arafat1419.codingtest

import android.app.Application
import com.arafat1419.codingtest.core.di.networkModule
import com.arafat1419.codingtest.core.di.repositoryModule
import com.arafat1419.codingtest.di.useCaseModule
import com.arafat1419.codingtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // startKoin use to make koin create injection to all descendant application like activity and fragment
        startKoin {
            androidLogger(Level.NONE)
            // Provide function that need context
            androidContext(this@BaseApp)
            // Add modules to koin
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}