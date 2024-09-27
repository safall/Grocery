package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "groceryItem",
    foreignKeys = [
        ForeignKey(
            entity = MasterGroceryEntity::class,
            parentColumns = ["id"],
            childColumns = ["groceryId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MasterGroceryItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["id"]
)
data class GroceryItemEntity(
    val id: String,
    val groceryId: String,
    val lastPurchasePrice: String,
    val isSelected: Boolean
)
