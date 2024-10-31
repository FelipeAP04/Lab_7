package com.example.lab7.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supermarket_items")
data class SupermarketItemEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,

    @ColumnInfo(name = "itemName")
    val itemName: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int
)