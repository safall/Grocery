package com.safal.android.dbpg.databse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskOwner")
data class TaskOwnerEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val address: String
)
