package com.whitecatlabs.grocery.main.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.whitecatlabs.grocery.main.databse.MyDatabase
import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryItemDao
import com.whitecatlabs.grocery.main.databse.migrations.MigrationModule
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.repository.GroceryRepositoryDefault
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [MigrationModule::class])
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
        migrations: java.util.Set<Migration>
    ): MyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "mydatabase.db",
        )
            .addMigrations(*migrations.toTypedArray<Migration>())
            .build()
    }


    @Provides
    @Singleton
    fun provideMasterGroceryDao(database: MyDatabase): MasterGroceryDao {
        return database.masterGroceryDao()
    }

    @Provides
    @Singleton
    fun provideMasterGroceryItemDao(database: MyDatabase): MasterGroceryItemDao {
        return database.masterGroceryItemDao()
    }

    @Provides
    @Singleton
    fun provideGroceryCategoryDao(database: MyDatabase): GroceryCategoryDao {
        return database.groceryCategoryDao()
    }

    @Provides
    @Singleton
    fun provideGroceryItemDao(database: MyDatabase): GroceryItemDao {
        return database.groceryItemDao()
    }

    @Provides
    @Singleton
    fun provideGroceryRepository(impl: GroceryRepositoryDefault): GroceryRepository {
        return impl
    }
}
