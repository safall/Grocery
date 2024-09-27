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
interface MasterGroceryDao {

    @Upsert
    @Transaction
    suspend fun insert(vararg item: MasterGroceryEntity)

    @Query("SELECT * FROM master_grocery")
    fun fetchAll(): Flow<List<MasterCategoryWithSelecte>>
}

data class MasterCategoryWithSelecte(
    @Embedded
    val item: MasterGroceryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val selectedItem: GroceryCategoryEntity?
)
