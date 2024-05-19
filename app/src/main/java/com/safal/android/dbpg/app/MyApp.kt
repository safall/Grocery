package com.safal.android.dbpg.app

import android.app.Application
import com.safal.android.dbpg.di.DaggerMyAppComponent
import com.safal.android.dbpg.di.MyAppComponent

class MyApp : Application() {

    lateinit var applicationComponent: MyAppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerMyAppComponent.builder().context(this).build()
    }
}
