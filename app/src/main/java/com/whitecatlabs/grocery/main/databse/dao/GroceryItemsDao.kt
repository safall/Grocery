package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Upsert
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryItemDao {
    @Upsert
    @Transaction
    suspend fun insert(vararg item: GroceryItemEntity)

    @Query("SELECT * FROM groceryItem")
    fun fetchAll(): Flow<List<GroceryItemEntity>>

    @Query("SELECT * FROM master_grocery_item WHERE groceryId = :groceryId")
    fun fetchItemsWithSelected(groceryId: String): Flow<List<ItemWithSelected>>
}

data class ItemWithSelected(
    @Embedded
    val item: MasterGroceryItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val selectedItem: GroceryItemEntity?
)
