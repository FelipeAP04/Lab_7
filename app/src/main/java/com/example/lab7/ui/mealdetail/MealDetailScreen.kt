package com.example.lab7.ui.mealdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Composable
fun MealDetailScreen(viewModel: MealDetailViewModel) {
    val mealDetails = viewModel.mealDetails.collectAsState().value

    mealDetails?.let { meal ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = meal.strMeal)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Instructions:")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = meal.strInstructions)
        }
    } ?: run {
        Text(text = "Loading...", modifier = Modifier.padding(16.dp))
    }
}