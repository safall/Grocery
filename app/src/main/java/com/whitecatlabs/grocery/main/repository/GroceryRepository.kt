package com.whitecatlabs.grocery.main.repository

import com.whitecatlabs.grocery.main.databse.dao.CategoryWithSelected
import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.ItemWithSelected
import com.whitecatlabs.grocery.main.databse.dao.MasterCategoryWithSelecte
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface GroceryRepository {
    suspend fun insertMasterGrocery(items: List<MasterGroceryEntity>)
    suspend fun insertMasterGroceryItem(items: List<MasterGroceryItemEntity>)
    suspend fun insertGroceryCategories(items: List<GroceryCategoryEntity>)
    fun getAllMasterCategories(): Flow<List<MasterCategoryWithSelecte>>
    fun getAllGroceryCategories(): Flow<List<CategoryWithSelected>>
    fun getItemsWithSelection(id: String): Flow<List<ItemWithSelected>>
    suspend fun updateItemSelection(groceryId: String, id: String, isSelected: Boolean)
}

@Singleton
class GroceryRepositoryDefault @Inject constructor(
    private val masterGroceryDao: MasterGroceryDao,
    private val masterGroceryItemDao: MasterGroceryItemDao,
    private val groceryCategoryDao: GroceryCategoryDao,
    private val groceryItemDao: GroceryItemDao,
) : GroceryRepository {
    override suspend fun insertMasterGrocery(items: List<MasterGroceryEntity>) {
        return masterGroceryDao.insert(*items.toTypedArray())
    }

    override suspend fun insertMasterGroceryItem(items: List<MasterGroceryItemEntity>) {
        return masterGroceryItemDao.insert(*items.toTypedArray())
    }

    override suspend fun insertGroceryCategories(items: List<GroceryCategoryEntity>) {
        return groceryCategoryDao.insert(*items.toTypedArray())
    }

    override fun getAllMasterCategories(): Flow<List<MasterCategoryWithSelecte>> {
        return masterGroceryDao.fetchAll()
    }

    override fun getAllGroceryCategories(): Flow<List<CategoryWithSelected>> {
        return groceryCategoryDao.fetchAll()
    }

    override fun getItemsWithSelection(id: String): Flow<List<ItemWithSelected>> {
        return groceryItemDao.fetchItemsWithSelected(id)
    }

    override suspend fun updateItemSelection(groceryId: String, id: String, isSelected: Boolean) {
        return withContext(Dispatchers.IO) {
            groceryItemDao.insert(
                GroceryItemEntity(
                    id = id,
                    groceryId = groceryId,
                    isSelected = isSelected,
                    lastPurchasePrice = ""
                )
            )
        }
    }
}
