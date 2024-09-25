package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("ClassNaming", "MagicNumber")
@Singleton
class Migration_3_4 @Inject constructor() : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(CREATE_MASTER_CATEGORY_TABLE)
        db.execSQL(CREATE_MASTER_ITEM_TABLE)
    }

    companion object {
        private const val CREATE_MASTER_CATEGORY_TABLE = """
        CREATE TABLE IF NOT EXISTS master_grocery (
            id TEXT NOT NULL,
            title TEXT NOT NULL,
            color TEXT NOT NULL,
            PRIMARY KEY(id)
        )
        """
        private const val CREATE_MASTER_ITEM_TABLE = """
        CREATE TABLE IF NOT EXISTS master_grocery_item (
            id TEXT NOT NULL,
            groceryId TEXT NOT NULL,
            title TEXT NOT NULL,
            PRIMARY KEY(id),
            FOREIGN KEY(groceryId) REFERENCES master_grocery(id) ON DELETE CASCADE
        )
    """
    }
}
