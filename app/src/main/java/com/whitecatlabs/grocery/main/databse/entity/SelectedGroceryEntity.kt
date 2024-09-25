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
    primaryKeys = ["groceryItemId"]
)
data class SelectedGroceryEntity(
    val groceryItemId: String,
    val isSelected: Boolean
)
