package com.whitecatlabs.grocery.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecatlabs.grocery.main.databse.dao.TaskDao
import com.whitecatlabs.grocery.main.databse.dao.TaskOwnerDao
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Loading
import com.whitecatlabs.grocery.main.ui.main.MainContract.ViewState.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    taskDao: TaskDao,
    taskOwnerDao: TaskOwnerDao
) : ViewModel() {

    val uiState: StateFlow<ViewState> = combine(
        taskDao.fetchAllTask(),
        taskOwnerDao.fetchAllTaskOwner()
    ) { taskOwnerEntities, taskEntities ->
        delay(3000)
        Result(taskOwnerEntities, taskEntities)
    }
        .onStart { Loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Loading
        )
}
