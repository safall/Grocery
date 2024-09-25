package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity

@Entity(
    tableName = "master_grocery",
    primaryKeys = ["id"]
)
data class MasterGroceryEntity(
    val id: String,
    val title: String,
    val color: String
)
