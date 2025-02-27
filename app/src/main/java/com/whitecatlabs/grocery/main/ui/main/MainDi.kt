package com.whitecatlabs.grocery.main.ui.main

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainDi = module {
    viewModelOf(::MainViewModel)
}