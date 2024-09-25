package com.whitecatlabs.grocery.main.repository

import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.ItemWithSelected
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryDao
import com.whitecatlabs.grocery.main.databse.dao.MasterGroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.SelectedGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryEntity
import com.whitecatlabs.grocery.main.databse.entity.MasterGroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface GroceryRepository {
    suspend fun insertMasterGrocery(items: List<MasterGroceryEntity>)
    suspend fun insertMasterGroceryItem(items: List<MasterGroceryItemEntity>)
    suspend fun insertGroceryCategories(vararg items: GroceryCategoryEntity)
    fun getAllGroceryCategories(): Flow<List<GroceryCategoryEntity>>
    fun getItemsWithSelection(id: String): Flow<List<ItemWithSelected>>
    suspend fun insertGroceryItems(vararg items: GroceryItemEntity)
    suspend fun insertGrocerySelectedItem(vararg items: SelectedGroceryEntity)
    suspend fun updateItemSelection(id: String, isSelected: Boolean)
}

@Singleton
class GroceryRepositoryDefault @Inject constructor(
    private val masterGroceryDao: MasterGroceryDao,
    private val masterGroceryItemDao: MasterGroceryItemDao,
    private val groceryCategoryDao: GroceryCategoryDao,
    private val groceryItemDao: GroceryItemDao,
    private val selectedGroceryItemDao: SelectedGroceryItemDao
) : GroceryRepository {
    override suspend fun insertMasterGrocery(items: List<MasterGroceryEntity>) {
        return masterGroceryDao.insert(*items.toTypedArray())
    }

    override suspend fun insertMasterGroceryItem(items: List<MasterGroceryItemEntity>) {
        return masterGroceryItemDao.insert(*items.toTypedArray())
    }

    override suspend fun insertGroceryCategories(vararg items: GroceryCategoryEntity) {
        return groceryCategoryDao.insert(*items)
    }

    override fun getAllGroceryCategories(): Flow<List<GroceryCategoryEntity>> {
        return groceryCategoryDao.fetchAll()
    }

    override fun getItemsWithSelection(id: String): Flow<List<ItemWithSelected>> {
        return groceryItemDao.fetchItemsWithSelected(id)
    }

    override suspend fun insertGroceryItems(vararg items: GroceryItemEntity) {
        return groceryItemDao.insert(*items)
    }

    override suspend fun insertGrocerySelectedItem(vararg items: SelectedGroceryEntity) {
        return selectedGroceryItemDao.insert(*items)
    }

    override suspend fun updateItemSelection(id: String, isSelected: Boolean) {
        return withContext(Dispatchers.IO) {
            selectedGroceryItemDao.update(
                SelectedGroceryEntity(
                    groceryItemId = id,
                    isSelected = isSelected
                )
            )
        }
    }
}
