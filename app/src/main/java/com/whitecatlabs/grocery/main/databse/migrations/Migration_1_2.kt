package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("ClassNaming", "MagicNumber")
@Singleton
class Migration_1_2 @Inject constructor() : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(DROP_TASK_TABLE)
        db.execSQL(DROP_TASK_OWNER_TABLE)
        db.execSQL(CREATE_GROCERY_CATEGORY_TABLE)
        db.execSQL(CREATE_GROCERY_ITEM_TABLE)
        db.execSQL(CREATE_SELECTED_GROCERY_ITEM_TABLE)
    }

    companion object {
        private const val DROP_TASK_TABLE = """DROP TABLE IF EXISTS task"""
        private const val DROP_TASK_OWNER_TABLE = """DROP TABLE IF EXISTS taskOwner"""
        private const val CREATE_GROCERY_CATEGORY_TABLE =
            """
        CREATE TABLE IF NOT EXISTS groceryCategory (
           id TEXT NOT NULL, 
           title TEXT NOT NULL,
           description TEXT NOT NULL,
           color TEXT NOT NULL,
           PRIMARY KEY(id)
        )
    """
        private const val CREATE_GROCERY_ITEM_TABLE =
            """
        CREATE TABLE IF NOT EXISTS groceryItem (
          itemId TEXT NOT NULL, 
          groceryId TEXT NOT NULL,
          title TEXT NOT NULL,
          description TEXT NOT NULL,
          lastPurchasePrice TEXT NULLABLE,
          PRIMARY KEY(itemId),
          FOREIGN KEY(groceryId) REFERENCES groceryCategory(id) ON DELETE CASCADE
        )
    """

        private const val CREATE_SELECTED_GROCERY_ITEM_TABLE =
            """
        CREATE TABLE IF NOT EXISTS selectedGroceryItem (
            id TEXT NOT NULL,
            groceryItemId TEXT NOT NULL,
            isSelected INTEGER NOT NULL,
            PRIMARY KEY(id),
            FOREIGN KEY(groceryItemId) REFERENCES groceryItem(itemId) ON DELETE CASCADE
        )
    """
    }
}
