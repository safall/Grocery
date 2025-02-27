package com.whitecatlabs.grocery.main.ui.addcategory

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val addCategoryDi = module {
    viewModelOf(::AddCategoryViewModel)
}
