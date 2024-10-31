package com.example.lab7.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "instructions")
    val instructions: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String


)