package com.whitecatlabs.grocery.main.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.whitecatlabs.grocery.main.databse.MyDatabase
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.repository.GroceryRepositoryDefault
import org.koin.dsl.module

val databaseModule = module {
    single { provideDataBase(get(), get()) }
    single { provideMasterGroceryDao(get()) }
    single { provideMasterGroceryItemDao(get()) }
    single { provideGroceryCategoryDao(get()) }
    single { provideGroceryItemDao(get()) }
    single<GroceryRepository> { GroceryRepositoryDefault(get(), get(), get(), get()) }
}

fun provideDataBase(context: Context, migrations: Set<Migration>): MyDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        MyDatabase::class.java,
        "mydatabase.db",
    )
        .addMigrations(*migrations.toTypedArray<Migration>())
        .build()
}

fun provideMasterGroceryDao(database: MyDatabase) = database.masterGroceryDao()
fun provideMasterGroceryItemDao(database: MyDatabase) = database.masterGroceryItemDao()
fun provideGroceryCategoryDao(database: MyDatabase) = database.groceryCategoryDao()
fun provideGroceryItemDao(database: MyDatabase) = database.groceryItemDao()
