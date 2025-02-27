package com.whitecatlabs.grocery.main.ui

import com.whitecatlabs.grocery.main.ui.addcategory.addCategoryDi
import com.whitecatlabs.grocery.main.ui.items.itemsDi
import com.whitecatlabs.grocery.main.ui.main.mainDi
import org.koin.dsl.module

val screensModule = module {
    includes(
        addCategoryDi,
        itemsDi,
        mainDi
    )
}
