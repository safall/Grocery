package com.safal.android.dbpg.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safal.android.dbpg.databse.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg task: TaskEntity)

    @Query("SELECT * FROM task")
    suspend fun fetchAllTask(): List<TaskEntity>
}

