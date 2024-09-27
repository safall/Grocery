package com.whitecatlabs.grocery.main.ui.main

import com.whitecatlabs.grocery.main.databse.dao.CategoryWithSelected

class MainContract {
    sealed class ViewState {
        data object Empty : ViewState()
        data class Result(val groceryCategories: List<CategoryWithSelected>) : ViewState()
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
