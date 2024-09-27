package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MasterGroceryItemDao {
    @Upsert
    @Transaction
    suspend fun insert(vararg item: MasterGroceryItemEntity)

    @Query("SELECT * FROM master_grocery_item")
    fun fetchAll(): Flow<List<MasterGroceryItemEntity>>
}
