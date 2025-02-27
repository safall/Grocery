package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import org.koin.dsl.module

val migrationsModule = module {
    single { Migration_1_2() }
    single { Migration_2_3() }
    single { Migration_3_4() }
    single { Migration_4_5() }

    single<Set<Migration>> {
        setOf(
            get<Migration_1_2>(),
            get<Migration_2_3>(),
            get<Migration_3_4>(),
            get<Migration_4_5>()
        )
    }
}
