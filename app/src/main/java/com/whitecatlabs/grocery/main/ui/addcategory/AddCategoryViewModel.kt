package com.whitecatlabs.grocery.main.ui.addcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecatlabs.grocery.main.databse.dao.MasterCategoryWithSelecte
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryContract.ViewState
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryContract.ViewState.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val repository: GroceryRepository
) : ViewModel() {

    val uiState: StateFlow<ViewState> = getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading,
        )

    private fun getAllCategories(): Flow<ViewState> {
        return try {
            repository.getAllMasterCategories()
                .map { result ->
                    ViewState.Result(result.map { it.toViewState() })
                }
        } catch (e: Exception) {
            e.printStackTrace()
            flow { ViewState.Error }
        }
    }

    private fun MasterCategoryWithSelecte.toViewState(): CategoryViewState {
        return CategoryViewState(
            id = item.id,
            title = item.title,
            color = item.color,
            isSelected = selectedItem?.isSelected ?: false
        )
    }

    fun consumeEvent(event: AddCategoryContract.Event) {
        when (event) {
            is AddCategoryContract.Event.BackButtonClickedEvent -> Unit
            is AddCategoryContract.Event.ItemCheckedEvent -> {
                val viewState = uiState.value as? ViewState.Result ?: return
                val item = viewState.items.find { it.id == event.id } ?: return
                viewModelScope.launch {
                    repository.insertGroceryCategories(
                        listOf(
                            GroceryCategoryEntity(
                                id = item.id,
                                isSelected = event.isSelected
                            )
                        )
                    )
                }
            }
        }
    }
}
