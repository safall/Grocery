package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.TaskOwnerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskOwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskOwnerEntity)

    @Query("SELECT * FROM taskOwner")
    fun fetchAllTaskOwner(): Flow<List<TaskOwnerEntity>>
}
