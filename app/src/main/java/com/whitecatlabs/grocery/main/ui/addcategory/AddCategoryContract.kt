package com.whitecatlabs.grocery.main.ui.addcategory

interface AddCategoryContract {

    sealed interface Event {
        data object BackButtonClickedEvent : Event
        data class ItemCheckedEvent(
            val id: String,
            val isSelected: Boolean
        ) : Event
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
    val color: String,
    val isSelected: Boolean
)
