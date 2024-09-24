package com.whitecatlabs.grocery.main.repository

import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.ItemWithSelected
import com.whitecatlabs.grocery.main.databse.dao.SelectedGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

interface GroceryRepository {
    suspend fun insertGroceryCategories(vararg items: GroceryCategoryEntity)
    fun getAllGroceryCategories(): Flow<List<GroceryCategoryEntity>>
    fun getItemsWithSelection(id: String): Flow<List<ItemWithSelected>>
    suspend fun insertGroceryItems(vararg items: GroceryItemEntity)
    suspend fun insertGrocerySelectedItem(vararg items: SelectedGroceryEntity)
    suspend fun updateItemSelection(id: String, isSelected: Boolean)
}

@Singleton
class GroceryRepositoryDefault @Inject constructor(
    private val groceryCategoryDao: GroceryCategoryDao,
    private val groceryItemDao: GroceryItemDao,
    private val selectedGroceryItemDao: SelectedGroceryItemDao
) : GroceryRepository {
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
                    id = UUID.randomUUID().toString(),
                    groceryItemId = id,
                    isSelected = isSelected
                )
            )
        }
    }
}
