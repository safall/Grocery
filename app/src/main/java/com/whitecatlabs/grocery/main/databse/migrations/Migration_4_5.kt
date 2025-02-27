package com.whitecatlabs.grocery.main.databse.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Suppress("ClassNaming", "MagicNumber")
class Migration_4_5 : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(DROP_GROCERY)
        db.execSQL(DROP_GROCERY_ITEM)
        db.execSQL(DROP_SELECTED_GROCERY_ITEM)
        db.execSQL(CREATE_GROCERY_CATEGORY_TABLE)
        db.execSQL(CREATE_GROCERY_ITEM_TABLE)
    }

    companion object {
        private const val DROP_GROCERY = "DROP TABLE IF EXISTS groceryCategory"
        private const val DROP_GROCERY_ITEM = "DROP TABLE IF EXISTS groceryItem"
        private const val DROP_SELECTED_GROCERY_ITEM = "DROP TABLE IF EXISTS selectedGroceryItem"
        private const val CREATE_GROCERY_CATEGORY_TABLE = """
        CREATE TABLE IF NOT EXISTS groceryCategory (
           id TEXT NOT NULL, 
           isSelected INTEGER NOT NULL DEFAULT 0,
           PRIMARY KEY(id),
           FOREIGN KEY(id) REFERENCES master_grocery(id) ON DELETE CASCADE ON UPDATE CASCADE
        )
        """

        private const val CREATE_GROCERY_ITEM_TABLE = """
        CREATE TABLE IF NOT EXISTS groceryItem (
          id TEXT NOT NULL, 
          groceryId TEXT NOT NULL,
          isSelected INTEGER NOT NULL DEFAULT 0,
          lastPurchasePrice TEXT NOT NULL DEFAULT "",
          PRIMARY KEY(id),
          FOREIGN KEY(id) REFERENCES master_grocery_item(id) ON DELETE CASCADE ON UPDATE CASCADE
          FOREIGN KEY(groceryId) REFERENCES master_grocery(id) ON DELETE CASCADE ON UPDATE CASCADE 
        )
        """
    }
}
