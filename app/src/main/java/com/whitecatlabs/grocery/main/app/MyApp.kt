package com.whitecatlabs.grocery.main.app

import android.app.Application
import com.whitecatlabs.grocery.main.di.DaggerMyAppComponent
import com.whitecatlabs.grocery.main.di.MyAppComponent

class MyApp : Application() {

    lateinit var applicationComponent: MyAppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerMyAppComponent.builder().context(this).build()
    }
}
