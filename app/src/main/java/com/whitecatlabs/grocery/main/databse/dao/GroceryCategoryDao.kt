package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: GroceryCategoryEntity)

    @Query("SELECT * FROM groceryCategory")
    fun fetchAll(): Flow<List<GroceryCategoryEntity>>
}
