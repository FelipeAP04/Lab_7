package com.example.lab7.ui.repository

import com.example.lab7.data.MealDao
import com.example.lab7.data.MealEntity
import com.example.lab7.ui.Networking.MealApiService
import kotlinx.coroutines.flow.Flow

class MealRepository(private val apiService: MealApiService, private val MealDao: MealDao) {
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getMealsByCategory(category: String) = apiService.getMealsByCategory(category)
    suspend fun getMealDetails(id: String) = apiService.getMealDetails(id)

    suspend fun fetchAndSaveCategories() {
        val response = apiService.getCategories()
        val mealEntities = response.categories.map { category ->
            MealEntity(
                id = category.idCategory,
                name = category.strCategory,
                category = category.strCategory,
                instructions = category.strCategoryDescription,
                imageUrl = category.strCategoryThumb
            )
        }
        MealDao.insertAll(mealEntities)
    }
}