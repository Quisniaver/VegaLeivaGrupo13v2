package com.geektcg.tienda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.data.Producto
import com.geektcg.tienda.data.Repo
import com.geektcg.tienda.vm.CarritoViewModel

@Composable
fun ProductosScreen(
    onVerProducto: (Int) -> Unit,
    carritoVM: CarritoViewModel
) {
    var categoriaSeleccionada by remember { mutableStateOf("pokemon") }

    val colorFondo = when (categoriaSeleccionada) {
        "pokemon" -> Color(0xFFFFEB3B) // Amarillo
        "yugioh" -> Color(0xFF9C27B0)  // Morado
        "myl" -> Color(0xFF4CAF50)     // Verde
        else -> Color(0xFFFFD700)      // Dorado
    }

    Column(modifier = Modifier.fillMaxSize()) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoriaButton("pokemon", "Pokémon", Color(0xFFFFEB3B), categoriaSeleccionada) {
                categoriaSeleccionada = "pokemon"
            }
            CategoriaButton("yugioh", "Yu-Gi-Oh!", Color(0xFF9C27B0), categoriaSeleccionada) {
                categoriaSeleccionada = "yugioh"
            }
            CategoriaButton("myl", "MyL", Color(0xFF4CAF50), categoriaSeleccionada) {
                categoriaSeleccionada = "myl"
            }
            CategoriaButton("otros", "Otros", Color(0xFFFFD700), categoriaSeleccionada) {
                categoriaSeleccionada = "otros"
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo.copy(alpha = 0.15f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            val productosFiltrados = Repo.productos.filter {
                if (categoriaSeleccionada == "otros") it.categoria !in listOf("pokemon", "yugioh", "myl")
                else it.categoria == categoriaSeleccionada
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(productosFiltrados) { producto ->
                    ProductoCard(
                        producto = producto,
                        onVerProducto = onVerProducto,
                        carritoVM = carritoVM
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriaButton(
    id: String,
    texto: String,
    color: Color,
    categoriaSeleccionada: String,
    onClick: () -> Unit
) {
    val isSelected = id == categoriaSeleccionada
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) color else color.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .size(80.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = texto,
                color = Color.Black,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun ProductoCard(
    producto: Producto,
    onVerProducto: (Int) -> Unit,
    carritoVM: CarritoViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onVerProducto(producto.id) }
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        carritoVM.add(
                            productId = producto.id,
                            name = producto.nombre,
                            price = producto.precio,
                            qty = 1
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}
