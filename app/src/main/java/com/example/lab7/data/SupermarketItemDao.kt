package com.example.lab7.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SupermarketItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: SupermarketItemEntity)

    @Update
    suspend fun update(item: SupermarketItemEntity)

    @Query("SELECT * FROM supermarket_items")
    suspend fun getAllItems(): List<SupermarketItemEntity>

    @Query("DELETE FROM supermarket_items WHERE id = :itemId")
    suspend fun deleteItem(itemId: String)
}