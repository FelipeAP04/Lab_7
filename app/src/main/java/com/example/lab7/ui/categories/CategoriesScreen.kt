package com.example.lab7.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lab7.ui.model.Category
import androidx.compose.foundation.lazy.items

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel, onCategorySelected: (String) -> Unit) {
    val categories = viewModel.categories.collectAsState().value

    LazyColumn {
        items(categories) { category ->
            CategoryItem(category) {
                onCategorySelected(category.strCategory)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(category.strCategoryThumb),
            contentDescription = category.strCategory,
            modifier = Modifier.size(64.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = category.strCategory)
    }
}