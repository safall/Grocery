package com.whitecatlabs.grocery.main.databse.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Upsert
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryCategoryDao {

    @Upsert
    @Transaction
    suspend fun insert(vararg category: GroceryCategoryEntity)

    @Query("SELECT * FROM groceryCategory WHERE isSelected =1")
    fun fetchAll(): Flow<List<CategoryWithSelected>>
}


data class CategoryWithSelected(
    @Embedded
    val selected: GroceryCategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val item: MasterGroceryEntity
)
