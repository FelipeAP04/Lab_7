package com.example.lab7.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodCategoryDao {
    @Insert
    suspend fun insert(foodCategory: FoodCategory)

    @Update
    suspend fun update(foodCategory: FoodCategory)

    @Delete
    suspend fun delete(foodCategory: FoodCategory)

    @Query("SELECT * from food_categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<FoodCategory>>

    @Query("SELECT * from food_categories WHERE id = :id")
    fun getCategory(id: Int): Flow<FoodCategory>
}
