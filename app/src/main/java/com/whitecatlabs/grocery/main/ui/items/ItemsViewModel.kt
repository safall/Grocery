package com.whitecatlabs.grocery.main.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.ui.items.ItemsContract.ViewState
import com.whitecatlabs.grocery.main.ui.items.ItemsContract.ViewState.Loading
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ItemsViewModel.Factory::class)
class ItemsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val repository: GroceryRepository
) : ViewModel() {

    val uiState: StateFlow<ViewState> = getAllItems(id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading,
        )

    private fun getAllItems(id: String): Flow<ViewState> {
        return try {
            repository.getItemsWithSelection(id).map {
                ViewState.Result(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            flow { ViewState.Error }
        }
    }

    fun consumeEvent(event: ItemsContract.Event) {
        when (event) {
            is ItemsContract.Event.ItemCheckedEvent ->
                viewModelScope.launch {
                    repository.updateItemSelection(
                        event.id,
                        event.isChecked
                    )
                }
            else -> Unit
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): ItemsViewModel
    }
}
