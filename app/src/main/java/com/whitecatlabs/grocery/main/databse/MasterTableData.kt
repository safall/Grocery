package com.whitecatlabs.grocery.main.databse

import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity

object MasterTableData {
    val groceries = listOf(
        MasterGroceryEntity(
            id = "1",
            title = "Vegetables",
            color = "#008000"
        ),
        MasterGroceryEntity(
            id = "2",
            title = "Meat",
            color = "#800000"
        ),
        MasterGroceryEntity(
            id = "3",
            title = "Fruits",
            color = "#FF7F50"
        ),
        MasterGroceryEntity(
            id = "4",
            title = "Cleaning Items",
            color = "#0080FF"
        )
    )

    val groceryItems = listOf(
        MasterGroceryItemEntity(
            id = "1",
            groceryId = "1",
            title = "Potato",
        ),
        MasterGroceryItemEntity(
            id = "2",
            groceryId = "1",
            title = "Tomato",
        ),
        MasterGroceryItemEntity(
            id = "3",
            groceryId = "1",
            title = "Spinach",
        ),
        MasterGroceryItemEntity(
            id = "4",
            groceryId = "2",
            title = "Chicken",
        ),
        MasterGroceryItemEntity(
            id = "5",
            groceryId = "2",
            title = "Fish",
        ),
        MasterGroceryItemEntity(
            id = "6",
            groceryId = "3",
            title = "Apple"
        ),
        MasterGroceryItemEntity(
            id = "7",
            groceryId = "3",
            title = "Banana"
        ),
        MasterGroceryItemEntity(
            id = "8",
            groceryId = "3",
            title = "Mango"
        ),
        MasterGroceryItemEntity(
            id = "9",
            groceryId = "4",
            title = "Detergent"
        ),
        MasterGroceryItemEntity(
            id = "10",
            groceryId = "4",
            title = "Softner"
        ),
        MasterGroceryItemEntity(
            id = "11",
            groceryId = "4",
            title = "Hand wash"
        ),
        MasterGroceryItemEntity(
            id = "12",
            groceryId = "4",
            title = "Dishwasher tablets"
        )
    )
}
