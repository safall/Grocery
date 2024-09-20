package com.whitecatlabs.grocery.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.whitecatlabs.grocery.main.app.MyApp
import com.whitecatlabs.grocery.main.databse.dao.TaskDao
import com.whitecatlabs.grocery.main.databse.dao.TaskOwnerDao
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Loading
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val taskDao: TaskDao,
    private val taskOwnerDao: TaskOwnerDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(Loading)
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()
        .onStart {
            taskOwnerDao.fetchAllTaskOwner()
                .combine(taskDao.fetchAllTask()) { taskOwnerEntities, taskEntities ->
                    Result(taskEntities, taskOwnerEntities)
                }
                .collect { _uiState.update { it } }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading
        )

    companion object {
        // Define ViewModel factory in a companion object

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MainViewModel(
                    (application as MyApp).applicationComponent.database.taskDao(),
                    application.applicationComponent.database.taskOwnerDao(),
                ) as T
            }
        }
    }
}
