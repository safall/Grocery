package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object MigrationModule {
    @IntoSet
    @Provides
    fun provideMigration_1_2(migration_1_2: Migration_1_2): Migration {
        return migration_1_2
    }
}
