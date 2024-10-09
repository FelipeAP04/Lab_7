package com.example.lab7.ui.theme.mealdetail

import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=52944"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface categoriesApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}