package com.whitecatlabs.grocery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.whitecatlabs.grocery.main.app.App
import com.whitecatlabs.grocery.main.ui.theme.AppTheme
import org.koin.androidx.compose.KoinAndroidContext

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                KoinAndroidContext {
                    App()
                }
            }
        }
    }
}
