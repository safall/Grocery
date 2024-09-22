package com.whitecatlabs.grocery.main.di

import android.content.Context
import androidx.room.Room
import com.whitecatlabs.grocery.main.databse.MyDatabase
import com.whitecatlabs.grocery.main.databse.dao.TaskDao
import com.whitecatlabs.grocery.main.databse.dao.TaskOwnerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "mydatabase.db",
        )
            .addMigrations()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: MyDatabase): TaskDao = database.taskDao()

    @Provides
    @Singleton
    fun provideTaskOwnerDao(database: MyDatabase): TaskOwnerDao = database.taskOwnerDao()
}
