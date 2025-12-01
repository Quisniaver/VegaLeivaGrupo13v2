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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.api.ApiClient
import com.geektcg.tienda.api.ProductoApi
import com.geektcg.tienda.api.ProductoRemote
import com.geektcg.tienda.R
import com.geektcg.tienda.vm.CarritoViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductosScreen(
    carritoVM: CarritoViewModel
) {
    val scope = rememberCoroutineScope()

    // API para traer productos
    val api = ApiClient.retrofit.create(ProductoApi::class.java)

    // Lista cargada desde backend
    var productos by remember { mutableStateOf<List<ProductoRemote>>(emptyList()) }

    // Categoría seleccionada
    var categoria by remember { mutableStateOf("Todos") }

    // Cargar productos cuando cambia la categoría
    LaunchedEffect(categoria) {
        productos = api.listarProductos(
            if (categoria == "Todos") null else categoria.lowercase()
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // ⭐ FILTROS DE CATEGORÍAS (chips bonitos)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoriaChip("Todos", categoria) { categoria = "Todos" }
            CategoriaChip("pokemon", categoria) { categoria = "pokemon" }
            CategoriaChip("yugioh", categoria) { categoria = "yugioh" }
            CategoriaChip("myl", categoria) { categoria = "myl" }
            CategoriaChip("accesorios", categoria) { categoria = "accesorios" }
        }

        // ⭐ Lista de productos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(productos) { p ->
                ProductoItem(p) {
                    carritoVM.add(
                        productId = p.id,
                        name = p.nombre,
                        price = p.precio,
                        qty = 1
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriaChip(
    text: String,
    selected: String,
    onClick: () -> Unit
) {
    Surface(
        color = if (text == selected) Color(0xFF0D47A1) else Color.White,
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .height(38.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.replaceFirstChar { it.uppercase() },
                color = if (text == selected) Color.White else Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProductoItem(producto: ProductoRemote, onAgregar: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Box {

            // ⭐ CHIP DE CATEGORÍA DENTRO DE LA CARD
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 12.dp)
                    .background(
                        color = Color(0xFF1976D2),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = producto.categoria.replaceFirstChar { it.uppercase() },
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = resolveImage(producto)),
                    contentDescription = producto.nombre,
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                    Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = onAgregar,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0D47A1)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Agregar al carrito", color = Color.White)
                    }
                }
            }
        }
    }
}

// ⭐ Traductor de imágenes
fun resolveImage(p: ProductoRemote): Int {
    return when (p.imagen) {
        "pokemon1" -> R.drawable.pokemon1
        "pokemon2" -> R.drawable.pokemon2
        "yugioh1"  -> R.drawable.yugioh1
        "myl1"     -> R.drawable.myl1
        else -> R.drawable.pokemon1
    }
}
