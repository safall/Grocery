package com.whitecatlabs.grocery.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecatlabs.grocery.main.repository.GroceryRepository
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
}
