package com.example.lab7.ui.theme.categories

import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/categories.php"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface CategoriesApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}

object categoriesApi {
    val retrofitService: CategoriesApiService by lazy {
        retrofit.create(CategoriesApiService::class.java)
    }
}