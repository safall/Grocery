package com.whitecatlabs.grocery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.whitecatlabs.grocery.main.ui.main.MainPage
import com.whitecatlabs.grocery.main.ui.main.MainViewModel
import com.whitecatlabs.grocery.main.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val viewState = viewModel.uiState.collectAsState().value
                MainPage(viewState)
            }
        }
    }
}
