package com.example.lab7.ui.supermarket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7.data.SupermarketItemEntity
import com.example.lab7.ui.repository.SupermarketItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupermarketItemViewModel(private val repository: SupermarketItemRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<SupermarketItemEntity>>(emptyList())
    val items: StateFlow<List<SupermarketItemEntity>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
        }
    }

    fun addItem(item: SupermarketItemEntity) {
        viewModelScope.launch {
            repository.insert(item)
            fetchItems()
        }
    }

    fun updateItem(item: SupermarketItemEntity) {
        viewModelScope.launch {
            repository.update(item)
            fetchItems()
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
            fetchItems()
        }
    }
}