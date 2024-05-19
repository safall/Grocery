package com.safal.android.dbpg.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safal.android.dbpg.databse.entity.TaskOwnerEntity

@Dao
interface TaskOwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskOwnerEntity)

    @Query("SELECT * FROM taskOwner")
    suspend fun fetchAllTaskOwner(): List<TaskOwnerEntity>
}
