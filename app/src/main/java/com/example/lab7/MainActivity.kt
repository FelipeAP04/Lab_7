package com.example.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab7.data.MealDatabase
import com.example.lab7.ui.Networking.RetrofitService
import com.example.lab7.ui.categories.CategoriesScreen
import com.example.lab7.ui.categories.CategoriesViewModel
import com.example.lab7.ui.mealdetail.MealDetailScreen
import com.example.lab7.ui.mealdetail.MealDetailViewModel
import com.example.lab7.ui.meals.MealsScreen
import com.example.lab7.ui.meals.MealsViewModel
import com.example.lab7.ui.repository.MealRepository
import com.example.lab7.ui.repository.SupermarketItemRepository
import com.example.lab7.ui.supermarket.SupermarketScreen
import com.example.lab7.ui.supermarket.SupermarketItemViewModel
import com.example.lab7.ui.theme.Lab7Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mealDatabase = MealDatabase.getDatabase(applicationContext)
            val mealDao = mealDatabase.mealDao()
            val supermarketItemDao = mealDatabase.supermarketItemDao() // Ensure you have this DAO
            val mealRepository = MealRepository(RetrofitService.apiService, mealDao)
            val supermarketItemRepository = SupermarketItemRepository(supermarketItemDao)

            lifecycleScope.launch {
                mealRepository.fetchAndSaveCategories()
            }

            NavHost(navController, startDestination = "categories") {
                composable("categories") {
                    val categoriesViewModel = CategoriesViewModel(mealRepository)
                    CategoriesScreen(categoriesViewModel, navController)
                }
                composable("meals/{category}") { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: "Seafood"
                    val mealsViewModel = MealsViewModel(mealRepository, category)
                    MealsScreen(mealsViewModel) { mealId ->
                        navController.navigate("mealDetail/$mealId")
                    }
                }
                composable("mealDetail/{mealId}") { backStackEntry ->
                    val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                    val mealDetailViewModel = MealDetailViewModel(mealRepository)
                    mealDetailViewModel.fetchMealDetails(mealId) // Fetch meal details
                    MealDetailScreen(mealDetailViewModel)
                }
                composable("supermarket") {
                    val supermarketItemViewModel = SupermarketItemViewModel(supermarketItemRepository)
                    SupermarketScreen(supermarketItemViewModel)
                }
            }
        }
    }
}