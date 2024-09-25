package com.whitecatlabs.grocery.main.databse.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "master_grocery_item",
    foreignKeys = [
        ForeignKey(
            entity = MasterGroceryEntity::class,
            parentColumns = ["id"],
            childColumns = ["groceryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["id"]
)
data class MasterGroceryItemEntity(
    val id: String,
    val groceryId: String,
    val title: String
)
