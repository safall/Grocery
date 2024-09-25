package com.whitecatlabs.grocery.main.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.SelectedGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import javax.inject.Singleton

@Database(
    entities = [
        GroceryCategoryEntity::class,
        SelectedGroceryEntity::class,
        GroceryItemEntity::class,
    ],
    version = 3,
    exportSchema = true,
)
@Singleton
abstract class MyDatabase : RoomDatabase() {
    abstract fun groceryCategoryDao(): GroceryCategoryDao
    abstract fun groceryItemDao(): GroceryItemDao
    abstract fun selectedGroceryItemDao(): SelectedGroceryItemDao
}
