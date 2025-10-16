package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geektcg.tienda.ui.vm.CarritoViewModel

@Composable
fun CarritoScreen(vm: CarritoViewModel = viewModel()) {
    val items = vm.items.collectAsState()
    val total = vm.total.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Carrito", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items.value) { it ->
                ListItem(
                    headlineContent = { Text(it.name) },
                    supportingContent = { Text("x${it.qty} - $" + it.price) }
                )
                Divider()
            }
        }
        Text("Total: $" + total.value, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { vm.clear() }, modifier = Modifier.weight(1f)) { Text("Vaciar") }
            Button(onClick = { /* pagar */ }, modifier = Modifier.weight(1f)) { Text("Pagar") }
        }
    }
}