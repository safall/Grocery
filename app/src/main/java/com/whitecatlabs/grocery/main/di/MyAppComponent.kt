package com.whitecatlabs.grocery.main.di

import android.content.Context
import com.whitecatlabs.grocery.main.MainActivity
import com.whitecatlabs.grocery.main.databse.MyDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface MyAppComponent {

    val database: MyDatabase

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): MyAppComponent
    }
}
