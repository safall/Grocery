package com.whitecatlabs.grocery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.whitecatlabs.grocery.main.app.MyApp
import com.whitecatlabs.grocery.main.ui.main.MainPage
import com.whitecatlabs.grocery.main.ui.main.MainViewModel
import com.whitecatlabs.grocery.main.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).applicationComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainPage()
            }
        }
    }
}
