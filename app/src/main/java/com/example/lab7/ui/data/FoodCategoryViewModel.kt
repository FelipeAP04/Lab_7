package com.example.lab7.ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class FoodCategoryViewModel(private val repository: FoodCategoryRepository) : ViewModel() {
    val allCategories: StateFlow<List<FoodCategory>> = repository
        .getAllCategoriesStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = emptyList()
        )

    suspend fun saveCategory(foodCategory: FoodCategory) {
        repository.insertCategory(foodCategory)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}