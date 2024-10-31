package com.example.lab7.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE MealEntity ADD COLUMN newColumnName INTEGER DEFAULT 0")

    }
}

@Database(entities = [MealEntity::class, SupermarketItemEntity::class], version = 2)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun supermarketItemDao(): SupermarketItemDao

    companion object {
        @Volatile
        private var Instance: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MealDatabase::class.java, "Meal_database")
                    .addMigrations(MIGRATION_1_2)
                    .build().also { Instance = it }
            }
        }
    }
}