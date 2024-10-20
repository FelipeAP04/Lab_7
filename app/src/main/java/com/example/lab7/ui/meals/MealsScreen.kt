package com.example.lab7.ui.meals

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lab7.ui.model.Meal

@Composable
fun MealsScreen(viewModel: MealsViewModel, onMealSelected: (String) -> Unit) {
    val meals = viewModel.meals.collectAsState().value

    LazyColumn {
        items(meals) { meal ->
            MealItem(meal) {
                onMealSelected(meal.idMeal)
            }
        }
    }
}

@Composable
fun MealItem(meal: Meal, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(meal.strMealThumb),
            contentDescription = meal.strMeal,
            modifier = Modifier.size(64.dp).padding(end = 16.dp)
        )
        Text(text = meal.strMeal, style = MaterialTheme.typography.bodyLarge)
    }
}