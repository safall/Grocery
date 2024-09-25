package com.whitecatlabs.grocery.main.ui.main

import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity

class MainContract {
    sealed class ViewState {
        data class Result(val groceryCategories: List<GroceryCategoryEntity>) : ViewState()
        data object Loading : ViewState()
        data object Error : ViewState()
    }

    sealed interface Event {
        data object AddButtonClickedEvent : Event
        data object BackButtonClickedEvent : Event
        data class ItemClickedEvent(
            val id: String,
            val title: String
        ) : Event
    }
}
