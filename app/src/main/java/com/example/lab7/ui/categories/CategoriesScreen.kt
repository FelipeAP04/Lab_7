package com.example.lab7.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lab7.ui.model.Category
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.lab7.R

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel, navController: NavController) {
    val categories = viewModel.categories.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("supermarket") }) {
                Icon(painter = painterResource(R.drawable.add_24dp_e8eaed_fill0_wght400_grad0_opsz24), contentDescription = "Add Supermarket Item")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(categories) { category ->
                CategoryItem(category) {
                    navController.navigate("meals/${category.strCategory}")
                }
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