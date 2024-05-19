package com.safal.android.dbpg.di

import android.content.Context
import androidx.room.Room
import com.safal.android.dbpg.databse.MyDatabase
import com.safal.android.dbpg.databse.dao.TaskDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): MyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "mydatabase.db"
        )
//            .addMigrations(
//                MyDatabase.migration1to2,
//                MyDatabase.migration2to3,
//                MyDatabase.migration3to4,
//                MyDatabase.migration4to5
//            )
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: MyDatabase): TaskDao = database.taskDao()

//    @Singleton
//    @Provides
//    fun provideTaskOwnerDao(database: MyDatabase): TaskOwnerDao = database.taskOwnerDao()
}
