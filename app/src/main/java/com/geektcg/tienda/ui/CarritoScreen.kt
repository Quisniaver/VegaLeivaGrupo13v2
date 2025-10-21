package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.vm.CarritoViewModel

@Composable
fun CarritoScreen(
    vm: CarritoViewModel,
    onPay: () -> Unit
) {
    val items by vm.items.collectAsState(initial = emptyList())
    val total = remember(items) { items.sumOf { it.price * it.qty } }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("🛒 Carrito de Compras", style = MaterialTheme.typography.titleLarge)
        Divider()

        if (items.isEmpty()) {
            Text("Tu carrito está vacío 😢", color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(item.name, fontWeight = FontWeight.SemiBold)
                                Text("Cantidad: ${item.qty}")
                                Text("Precio: $${String.format("%,d", item.price)} c/u")
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    "$${String.format("%,d", item.price * item.qty)}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(Modifier.height(6.dp))
                                OutlinedButton(
                                    onClick = { vm.removeItem(item) },
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Text("🗑 Borrar")
                                }
                            }
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium)
                Text("$${String.format("%,d", total)}", style = MaterialTheme.typography.titleMedium)
            }

            if (error != null) {
                Text(error!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        vm.clear()
                        error = null
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Vaciar 🧹")
                }

                Button(
                    onClick = {
                        // 🔹 Validaciones antes de pagar
                        when {
                            items.isEmpty() -> error = "El carrito está vacío."
                            total < 2000 -> error = "El total mínimo de compra es $2.000."
                            items.any { it.qty <= 0 } -> error = "Hay productos con cantidad inválida."
                            else -> {
                                error = null
                                onPay()
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("💳 Pagar")
                }
            }
        }
    }
}
