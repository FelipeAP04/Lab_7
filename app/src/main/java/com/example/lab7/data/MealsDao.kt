package com.example.lab7.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(mealCategories: List<MealEntity>)

    @Update
    suspend fun update(item: SupermarketItemEntity)

    @Query("SELECT * FROM meals")
    suspend fun getAllMealCategories(): List<MealEntity>
}