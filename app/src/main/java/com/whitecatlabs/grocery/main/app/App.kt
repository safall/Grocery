package com.whitecatlabs.grocery.main.app

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.whitecatlabs.grocery.main.ui.addcategory.AddCategoryScreen
import com.whitecatlabs.grocery.main.ui.items.ItemsScreen
import com.whitecatlabs.grocery.main.ui.main.MainContract
import com.whitecatlabs.grocery.main.ui.main.MainScreen
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
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val activity = LocalContext.current as Activity

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Main,
    ) {
        composable<NavigationDestination.Main> {
            MainScreen { event ->
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
            val categoryId = currentBackStackEntry?.arguments?.getString("categoryId") ?: ""
            val title = currentBackStackEntry?.arguments?.getString("title") ?: ""
            ItemsScreen(
                categoryId = categoryId,
                title = title,
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable<NavigationDestination.AddCategory> {
            AddCategoryScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
