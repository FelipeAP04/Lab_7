package com.example.lab7.ui.supermarket

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab7.data.SupermarketItemEntity

@Composable
fun SupermarketScreen(viewModel: SupermarketItemViewModel = viewModel()) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Supermarket Items", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (itemName.isNotEmpty() && quantity.isNotEmpty()) {
                val item = SupermarketItemEntity(
                    id = System.currentTimeMillis().toString(), // Generate a unique ID
                    itemName = itemName,
                    quantity = quantity.toIntOrNull() ?: 0 // Convert quantity to Int
                )
                viewModel.addItem(item)
                itemName = ""
                quantity = ""
            }
        }) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of items
        val items by viewModel.items.collectAsState()
        LazyColumn {
            items(items) { item ->
                Text("${item.itemName} - Quantity: ${item.quantity}")
            }
        }
    }
}