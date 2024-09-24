package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "selectedGroceryItem",
    foreignKeys = [
        ForeignKey(
            entity = GroceryItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["groceryItemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["id"]
)
data class SelectedGroceryEntity(
    val id: String,
    val groceryItemId: String,
    val isSelected: Boolean
)
