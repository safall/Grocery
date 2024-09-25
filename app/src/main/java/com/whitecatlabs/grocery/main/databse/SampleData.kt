package com.whitecatlabs.grocery.main.databse

import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity

object SampleData {
    val items = listOf<String>()
    val categories = listOf(
        GroceryCategoryEntity(
            id = "1",
            title = "Vegetables",
            description = "All Vegetables should be in this category",
            color = "#008000"
        ),
        GroceryCategoryEntity(
            id = "2",
            title = "Meat",
            description = "All Meat should be in this category",
            color = "#800000"
        ),
        GroceryCategoryEntity(
            id = "3",
            title = "Fruits",
            description = "All Fruits should be in this category",
            color = "#FF7F50"
        )
    )

    val groceryItems = listOf(
        GroceryItemEntity(
            itemId = "1",
            groceryId = "1",
            title = "Potato",
            description = "Red Potato",
            lastPurchasePrice = "10.0"
        ),
        GroceryItemEntity(
            itemId = "2",
            groceryId = "1",
            title = "Tomato",
            description = "Cherry Tomato",
            lastPurchasePrice = "11.0"
        ),
        GroceryItemEntity(
            itemId = "3",
            groceryId = "1",
            title = "Spinach",
            description = "Paalak Spinach",
            lastPurchasePrice = "12.0"
        ),
        GroceryItemEntity(
            itemId = "4",
            groceryId = "2",
            title = "Chicken",
            description = "Solvinge",
            lastPurchasePrice = "78.0"
        ),
        GroceryItemEntity(
            itemId = "5",
            groceryId = "2",
            title = "Fish",
            description = "Salmon",
            lastPurchasePrice = "128.0"
        ),
        GroceryItemEntity(
            itemId = "6",
            groceryId = "3",
            title = "Apple",
            description = "",
            lastPurchasePrice = "14.0"
        ),
        GroceryItemEntity(
            itemId = "7",
            groceryId = "3",
            title = "Banana",
            description = "",
            lastPurchasePrice = "11.0"
        ),
        GroceryItemEntity(
            itemId = "8",
            groceryId = "3",
            title = "Mango",
            description = "",
            lastPurchasePrice = "12.0"
        )
    )

    val selectedItems = listOf(
        SelectedGroceryEntity(
            groceryItemId = "1",
            isSelected = true
        ),
        SelectedGroceryEntity(
            groceryItemId = "2",
            isSelected = true
        ),
        SelectedGroceryEntity(
            groceryItemId = "6",
            isSelected = true
        ),
        SelectedGroceryEntity(
            groceryItemId = "7",
            isSelected = true
        )
    )
}