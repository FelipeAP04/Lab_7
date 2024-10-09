package com.example.lab7.ui.theme.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CategoriesUiState {
    data class Success(val photos: String) : CategoriesUiState
    object Error : CategoriesUiState
    object Loading : CategoriesUiState
}

class MarsViewModel : ViewModel() {
    var categoriesUiState: CategoriesUiState by mutableStateOf(CategoriesUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            categoriesUiState = CategoriesUiState.Loading
            categoriesUiState = try {
                val listResult = categoriesApi.retrofitService.getPhotos()
                CategoriesUiState.Success(
                    "Fotos cargadas con exito"
                )
            } catch (e: IOException) {
                CategoriesUiState.Error
            } catch (e: HttpException) {
                CategoriesUiState.Error
            }
        }

    }
}