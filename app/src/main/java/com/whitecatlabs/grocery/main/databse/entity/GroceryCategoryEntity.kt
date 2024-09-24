package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity


@Entity(
    tableName = "groceryCategory",
    primaryKeys = ["id"]
)
data class GroceryCategoryEntity(
    val id: String,
    val title: String,
    val description: String,
    val color: String
)
