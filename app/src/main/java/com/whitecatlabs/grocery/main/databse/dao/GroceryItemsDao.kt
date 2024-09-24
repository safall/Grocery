package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: GroceryItemEntity)

    @Query("SELECT * FROM groceryItem")
    fun fetchAll(): Flow<List<GroceryItemEntity>>
}
