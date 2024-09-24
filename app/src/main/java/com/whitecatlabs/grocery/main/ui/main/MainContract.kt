package com.whitecatlabs.grocery.main.ui.main

import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity

class MainContract {
    sealed class ViewState {
        data class Result(val groceryCategories: List<GroceryCategoryEntity>) : ViewState()
        data object Loading : ViewState()
        data object Error: ViewState()
    }
}
