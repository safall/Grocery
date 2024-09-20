package com.whitecatlabs.grocery.main.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whitecatlabs.grocery.main.databse.dao.TaskDao
import com.whitecatlabs.grocery.main.databse.dao.TaskOwnerDao
import com.whitecatlabs.grocery.main.databse.entity.TaskEntity
import com.whitecatlabs.grocery.main.databse.entity.TaskOwnerEntity

@Database(
    entities = [
        TaskEntity::class,
        TaskOwnerEntity::class
    ], version = 1, exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun taskOwnerDao(): TaskOwnerDao
}
