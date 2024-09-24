package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedGroceryItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: SelectedGroceryEntity)

    @Query("SELECT * FROM selectedGroceryItem")
    fun fetchAll(): Flow<List<SelectedGroceryEntity>>
}
