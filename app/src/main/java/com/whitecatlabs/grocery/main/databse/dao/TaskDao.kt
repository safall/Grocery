package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whitecatlabs.grocery.main.databse.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg task: TaskEntity)

    @Query("SELECT * FROM task")
    fun fetchAllTask(): Flow<List<TaskEntity>>
}
