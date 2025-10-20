package com.geektcg.tienda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.data.Producto
import com.geektcg.tienda.data.Repo
@Composable

fun ProductosScreen(onVerProducto: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Pokémon
        item {
            SectionTitle("Pokémon")
        }
        items(Repo.productos.filter { it.categoria == "pokemon" }) { producto ->
            ProductoCard(producto = producto, onVerProducto = onVerProducto)
        }

        // Yu-Gi-Oh!
        item {
            SectionTitle("Yu-Gi-Oh!")
        }
        items(Repo.productos.filter { it.categoria == "yugioh" }) { producto ->
            ProductoCard(producto = producto, onVerProducto = onVerProducto)
        }

        // Mitos y Leyendas
        item {
            SectionTitle("Mitos y Leyendas")
        }
        items(Repo.productos.filter { it.categoria == "myl" }) { producto ->
            ProductoCard(producto = producto, onVerProducto = onVerProducto)
        }

        // Accesorios
        item {
            SectionTitle("Accesorios")
        }
        items(Repo.productos.filter { it.categoria == "accesorios" }) { producto ->
            ProductoCard(producto = producto, onVerProducto = onVerProducto)
        }
    }
}

@Composable
fun ProductoCard(producto: Producto, onVerProducto: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onVerProducto(producto.id) }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(90.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
