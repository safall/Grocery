package com.whitecatlabs.grocery.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GroceryRepository
) : ViewModel() {

    val uiState: StateFlow<ViewState> = getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading,
        )

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
        )
    )

    val selectedItems = listOf(
        SelectedGroceryEntity(
            id = "1",
            groceryItemId = "1",
            isSelected = true
        ),
        SelectedGroceryEntity(
            id = "2",
            groceryItemId = "2",
            isSelected = true
        ),
        SelectedGroceryEntity(
            id = "2",
            groceryItemId = "3",
            isSelected = true
        )
    )

    private fun getAllCategories(): Flow<ViewState> {
        return try {
            repository.getAllGroceryCategories().map {
                ViewState.Result(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            flow { ViewState.Error }
        }
    }

    init {
        viewModelScope.launch {
            delay(2000)
            repository.insertGroceryCategories(*categories.toTypedArray())
            repository.insertGroceryItems(*groceryItems.toTypedArray())
            repository.insertGrocerySelectedItem(*selectedItems.toTypedArray())
        }
    }
}
