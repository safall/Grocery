package com.whitecatlabs.grocery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.whitecatlabs.grocery.main.app.MyApp
import com.whitecatlabs.grocery.main.ui.main.MainPage
import com.whitecatlabs.grocery.main.ui.main.MainViewModel
import com.whitecatlabs.grocery.main.ui.theme.DBPGTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).applicationComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            DBPGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewState = viewModel.uiState.collectAsStateWithLifecycle().value
                    MainPage(
                        modifier = Modifier.padding(innerPadding),
                        viewState = viewState
                    )
                }
            }
        }
    }
}
