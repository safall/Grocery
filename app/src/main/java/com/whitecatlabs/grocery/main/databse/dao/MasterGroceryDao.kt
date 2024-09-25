package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MasterGroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: MasterGroceryEntity)

    @Query("SELECT * FROM master_grocery")
    fun fetchAll(): Flow<List<MasterGroceryEntity>>
}
