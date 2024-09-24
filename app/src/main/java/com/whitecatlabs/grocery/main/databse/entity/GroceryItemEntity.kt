package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "groceryItem",
    foreignKeys = [
        ForeignKey(
            entity = GroceryCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["groceryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["itemId"]
)
data class GroceryItemEntity(
    val itemId: String,
    val groceryId: String,
    val title: String,
    val description: String,
    val lastPurchasePrice: String?
)
