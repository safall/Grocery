package com.whitecatlabs.grocery.main.ui.items

import com.whitecatlabs.grocery.main.databse.dao.ItemWithSelected

class ItemsContract {
    sealed class ViewState {
        data class Result(val items: List<ItemWithSelected>) : ViewState()
        data object Loading : ViewState()
        data object Error : ViewState()
    }

    sealed interface Event {
        data object BackButtonClickedEvent : Event
        data class ItemClickedEvent(val id: String) : Event
        data class ItemCheckedEvent(val id: String, val isChecked: Boolean) : Event
    }
}
