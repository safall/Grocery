package com.whitecatlabs.grocery.main.app

import android.app.Application
import com.whitecatlabs.grocery.main.databse.migrations.migrationsModule
import com.whitecatlabs.grocery.main.di.databaseModule
import com.whitecatlabs.grocery.main.ui.screensModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MyApp)
            modules(
                migrationsModule,
                databaseModule,
                screensModule,
            )
        }
    }
}
