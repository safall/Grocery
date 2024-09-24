package com.whitecatlabs.grocery.main.repository

import com.whitecatlabs.grocery.main.databse.dao.GroceryCategoryDao
import com.whitecatlabs.grocery.main.databse.dao.GroceryItemDao
import com.whitecatlabs.grocery.main.databse.dao.SelectedGroceryItemDao
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.databse.entity.GroceryItemEntity
import com.whitecatlabs.grocery.main.databse.entity.SelectedGroceryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface GroceryRepository {
    suspend fun insertGroceryCategories(vararg items: GroceryCategoryEntity)
    fun getAllGroceryCategories(): Flow<List<GroceryCategoryEntity>>
    suspend fun insertGroceryItems(vararg items: GroceryItemEntity)
    suspend fun insertGrocerySelectedItem(vararg items: SelectedGroceryEntity)
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

    override suspend fun insertGroceryItems(vararg items: GroceryItemEntity) {
        return groceryItemDao.insert(*items)
    }

    override suspend fun insertGrocerySelectedItem(vararg items: SelectedGroceryEntity) {
        return selectedGroceryItemDao.insert(*items)
    }
}
