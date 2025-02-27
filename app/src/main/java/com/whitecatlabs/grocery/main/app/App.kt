package com.whitecatlabs.grocery.main.app

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryContract
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryScreen
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryViewModel
import com.whitecatlabs.grocery.main.ui.items.ItemsContract
import com.whitecatlabs.grocery.main.ui.items.ItemsScreen
import com.whitecatlabs.grocery.main.ui.items.ItemsViewModel
import com.whitecatlabs.grocery.main.ui.main.MainContract
import com.whitecatlabs.grocery.main.ui.main.MainScreen
import com.whitecatlabs.grocery.main.ui.main.MainViewModel
import kotlinx.serialization.Serializable


sealed interface NavigationDestination {
    @Serializable
    data object Main : NavigationDestination

    @Serializable
    data class Items(val categoryId: String, val title: String) : NavigationDestination

    @Serializable
    data object AddCategory : NavigationDestination
}

@Composable
fun App() {
    val navController = rememberNavController()
    val activity = LocalContext.current as Activity

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Main,
    ) {
        composable<NavigationDestination.Main> {
            val viewModel: MainViewModel = hiltViewModel()
            val viewState = viewModel.uiState.collectAsStateWithLifecycle().value
            MainScreen(viewState = viewState) { event ->
                when (event) {
                    is MainContract.Event.AddButtonClickedEvent -> navController.navigate(
                        NavigationDestination.AddCategory
                    )

                    is MainContract.Event.BackButtonClickedEvent -> activity.finish()
                    is MainContract.Event.ItemClickedEvent -> {
                        navController.navigate(
                            NavigationDestination.Items(
                                categoryId = event.id,
                                title = event.title
                            )
                        )
                    }
                }
            }
        }

        composable<NavigationDestination.Items> {
            val arguments = it.toRoute<NavigationDestination.Items>()
            val viewModel: ItemsViewModel = hiltViewModel(
                creationCallback = { factory: ItemsViewModel.Factory ->
                    factory.create(arguments.categoryId)
                }
            )
            val viewState = viewModel.uiState.collectAsStateWithLifecycle().value
            ItemsScreen(
                title = arguments.title,
                viewState = viewState
            ) { event ->
                when (event) {
                    is ItemsContract.Event.BackButtonClickedEvent -> {
                        navController.popBackStack()
                    }

                    is ItemsContract.Event.ItemClickedEvent -> Unit
                    is ItemsContract.Event.ItemCheckedEvent -> viewModel.consumeEvent(event)
                }
            }
        }

        composable<NavigationDestination.AddCategory> {
            val viewModel: AddCategoryViewModel = hiltViewModel()
            val viewState = viewModel.uiState.collectAsStateWithLifecycle().value
            AddCategoryScreen(viewState) { event ->
                when (event) {
                    is AddCategoryContract.Event.BackButtonClickedEvent -> {
                        navController.popBackStack()
                    }

                    is AddCategoryContract.Event.ItemCheckedEvent -> viewModel.consumeEvent(event)
                }
            }
        }
    }
}
