package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "groceryCategory",
    foreignKeys = [
        ForeignKey(
            entity = MasterGroceryEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["id"]
)
data class GroceryCategoryEntity(
    val id: String,
    val isSelected: Boolean
)
