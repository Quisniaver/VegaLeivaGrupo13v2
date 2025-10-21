package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geektcg.tienda.vm.CarritoViewModel

@Composable
fun PaymentResultScreen(
    navController: NavController,
    status: String,
    vm: CarritoViewModel
) {
    val items by vm.items.collectAsState(initial = emptyList())
    val total = remember(items) { items.sumOf { it.price * it.qty } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (status == "success") {
            Text(
                "✅ ¡Pago exitoso!",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("Gracias por tu compra. Total: $$total")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                vm.clear()
                navController.navigate("inicio")
            }) {
                Text("Volver al inicio")
            }
        } else {
            Text(
                "❌ Pago fallido",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("carrito") }) {
                Text("Reintentar")
            }
        }
    }
}
