package com.example.lab7.ui.repository

import com.example.lab7.data.SupermarketItemDao
import com.example.lab7.data.SupermarketItemEntity

class SupermarketItemRepository(private val supermarketItemDao: SupermarketItemDao) {
    suspend fun insert(item: SupermarketItemEntity) {
        supermarketItemDao.insert(item)
    }

    suspend fun update(item: SupermarketItemEntity) {
        supermarketItemDao.update(item)
    }

    suspend fun getAllItems(): List<SupermarketItemEntity> {
        return supermarketItemDao.getAllItems()
    }

    suspend fun deleteItem(itemId: String) {
        supermarketItemDao.deleteItem(itemId)
    }
}