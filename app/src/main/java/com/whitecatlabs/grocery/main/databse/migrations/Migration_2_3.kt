package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Suppress("ClassNaming", "MagicNumber")
class Migration_2_3 : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(CREATE_TEMP_SELECTED_GROCERY_ITEM_TABLE)
        db.execSQL(DROP_SELECTED_GROCERY_ITEM_TABLE)
        db.execSQL(RENAME_TEMP_TABLE_TO_ORIGINAL_NAME)
    }

    companion object {
        private const val CREATE_TEMP_SELECTED_GROCERY_ITEM_TABLE = """
        CREATE TABLE IF NOT EXISTS selectedGroceryItem_temp (
            groceryItemId TEXT NOT NULL,
            isSelected INTEGER NOT NULL,
            PRIMARY KEY(groceryItemId),
            FOREIGN KEY(groceryItemId) REFERENCES groceryItem(itemId) ON DELETE CASCADE
        )
    """
        private const val DROP_SELECTED_GROCERY_ITEM_TABLE =
            "DROP TABLE IF EXISTS selectedGroceryItem"

        private const val RENAME_TEMP_TABLE_TO_ORIGINAL_NAME =
            "ALTER TABLE selectedGroceryItem_temp RENAME TO selectedGroceryItem"
    }
}
