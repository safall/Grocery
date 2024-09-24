package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: GroceryItemEntity)

    @Query("SELECT * FROM groceryItem")
    fun fetchAll(): Flow<List<GroceryItemEntity>>

    @Query("SELECT * FROM groceryItem WHERE groceryId = :id")
    fun fetchItemsWithSelected(id: String): Flow<List<ItemWithSelected>>
}

data class ItemWithSelected(
    @Embedded
    val groceryItem: GroceryItemEntity,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "groceryItemId"
    )
    val selectedItem: SelectedGroceryEntity?
)
