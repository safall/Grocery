package com.whitecatlabs.grocery.main.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity
import javax.inject.Singleton

@Database(
    entities = [
        MasterGroceryEntity::class,
        MasterGroceryItemEntity::class,
        GroceryCategoryEntity::class,
        GroceryItemEntity::class,
    ],
    version = 5,
    exportSchema = true,
)
@Singleton
abstract class MyDatabase : RoomDatabase() {
    abstract fun masterGroceryDao(): MasterGroceryDao
    abstract fun masterGroceryItemDao(): MasterGroceryItemDao
    abstract fun groceryCategoryDao(): GroceryCategoryDao
    abstract fun groceryItemDao(): GroceryItemDao
}
