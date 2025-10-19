package com.geektcg.tienda.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geektcg.tienda.data.Repo
import com.geektcg.tienda.vm.CarritoViewModel

@Composable
fun DetalleScreen(
    id: Int,
    carritoVM: CarritoViewModel = viewModel()
) {
    // Buscar producto o usar el primero si no existe
    val producto = Repo.productos.find { it.id == id } ?: Repo.productos.first()
    var agregado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del producto
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del producto
        Text(
            text = producto.nombre,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Precio del producto
        Text(
            text = "Precio: $${producto.precio}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón agregar al carrito
        Button(
            onClick = {
                carritoVM.add(producto.id, producto.nombre, producto.precio, 1)
                agregado = true
            }
        ) {
            Text(text = "Agregar al Carrito")
        }

        // Mensaje de producto agregado
        AnimatedVisibility(visible = agregado) {
            Text(
                text = "¡Producto agregado!",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
