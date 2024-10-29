package com.example.lab7.ui.data

class FoodCategoryRepository(private val foodCategoryDao: FoodCategoryDao) {
    fun getAllCategoriesStream(): Flow<List<FoodCategory>> = foodCategoryDao.getAllCategories()

    fun getCategoryStream(id: Int): Flow<FoodCategory> = foodCategoryDao.getCategory(id)

    suspend fun insertCategory(foodCategory: FoodCategory) {
        foodCategoryDao.insert(foodCategory)
    }

    suspend fun updateCategory(foodCategory: FoodCategory) {
        foodCategoryDao.update(foodCategory)
    }

    suspend fun deleteCategory(foodCategory: FoodCategory) {
        foodCategoryDao.delete(foodCategory)
    }
}