package com.whitecatlabs.grocery.main.ui.main

import com.whitecatlabs.grocery.main.databse.entity.TaskEntity
import com.whitecatlabs.grocery.main.databse.entity.TaskOwnerEntity

class MainContract {
    sealed class ViewState {
        data class Result(
            val taskEntities: List<TaskEntity>,
            val taskOwnerEntities: List<TaskOwnerEntity>,
        ) : ViewState()

        data object Loading : ViewState()
    }
}
