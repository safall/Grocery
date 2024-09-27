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

    @IntoSet
    @Provides
    fun provideMigration__2_3(migration_2_3: Migration_2_3): Migration {
        return migration_2_3
    }

    @IntoSet
    @Provides
    fun provideMigration__3_4(migration_3_4: Migration_3_4): Migration {
        return migration_3_4
    }

    @IntoSet
    @Provides
    fun provideMigration_4_5(migration_4_5: Migration_4_5): Migration {
        return migration_4_5
    }
}
