package com.whitecatlabs.grocery.main.ui.addcategory

import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity

interface AddCategoryContract {

    sealed interface Event {
        data object BackButtonClickedEvent : Event
    }

    sealed class ViewState() {
        data class Result(val items: List<CategoryViewState>) : ViewState()
        data object Loading : ViewState()
        data object Error : ViewState()
    }
}

data class CategoryViewState(
    val id: String,
    val title: String,
    val color: String
)

fun MasterGroceryEntity.toViewState(): CategoryViewState {
    return CategoryViewState(
        id = id,
        title = title,
        color = color
    )
}