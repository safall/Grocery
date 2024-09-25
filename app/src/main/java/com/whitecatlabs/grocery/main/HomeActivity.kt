package com.whitecatlabs.grocery.main

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.whitecatlabs.grocery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity(R.layout.activity_main) {
    private val viewModel: HomeViewModel by viewModels()
}
