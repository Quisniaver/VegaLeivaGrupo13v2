package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.data.Repo
import com.geektcg.tienda.ui.ProductoCard

@Composable
fun InicioScreen(onVerProducto: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(Repo.productos) { p ->
            ProductoCard(producto = p, onVerProducto = onVerProducto)

        }
    }
}