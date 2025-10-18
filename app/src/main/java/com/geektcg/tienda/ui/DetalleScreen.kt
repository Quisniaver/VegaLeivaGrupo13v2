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
    val producto = Repo.productos.find { it.id == id } ?: Repo.productos.first()
    var agregado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del producto
        Image(
            painter = painterResource(id = producto.imagen),
            contentDescription = producto.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Precio: $${producto.precio}", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            carritoVM.add(producto.id, producto.nombre, producto.precio, 1)
            agregado = true
        }) {
            Text("Agregar al Carrito")
        }

        AnimatedVisibility(visible = agregado) {
            Text(
                "¡Producto agregado!",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
