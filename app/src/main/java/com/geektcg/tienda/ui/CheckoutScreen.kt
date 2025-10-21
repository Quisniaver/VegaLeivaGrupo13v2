package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.geektcg.tienda.vm.CarritoViewModel
import com.geektcg.tienda.vm.SessionManager

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    vm: CarritoViewModel = viewModel()
) {
    val items by vm.items.collectAsState()
    val total by vm.total.collectAsState(initial = 0)

    val currentUser = SessionManager.currentUser
    val isLoggedIn = currentUser != null

    var nombre by remember { mutableStateOf(currentUser?.nombre ?: "") }
    var email by remember { mutableStateOf(currentUser?.email ?: "") }
    var direccion by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }
    var pagoExitoso by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("💳 Checkout", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        // 🛒 Resumen del pedido
        Text("Resumen del pedido", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        if (items.isEmpty()) {
            Text("Tu carrito está vacío 😢", color = MaterialTheme.colorScheme.onSurfaceVariant)
            return
        }

        items.forEach { item ->
            val subtotal = item.price * item.qty
            Text("• ${item.name} x${item.qty} — $${String.format("%,d", subtotal)}")
        }

        Divider(Modifier.padding(vertical = 8.dp))
        Text(
            "Total: $${String.format("%,d", total)}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 Mostrar sesión
        if (isLoggedIn) {
            Text(
                "Sesión iniciada como: ${currentUser?.nombre}",
                color = MaterialTheme.colorScheme.primary
            )
            Text("Email: ${currentUser?.email}", color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            Text("Comprar sin iniciar sesión", color = MaterialTheme.colorScheme.secondary)
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 Campos condicionales (sin KeyboardOptions)
        if (!isLoggedIn) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección de envío") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }

        // 🔹 Botón de pago ficticio
        Button(
            onClick = {
                when {
                    items.isEmpty() -> error = "El carrito está vacío."
                    total <= 0 -> error = "El total debe ser mayor a $0."
                    direccion.isBlank() -> error = "Debes ingresar una dirección."
                    !isLoggedIn && nombre.isBlank() -> error = "Ingresa tu nombre."
                    !isLoggedIn && (email.isBlank() ||
                            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) ->
                        error = "Email inválido o vacío."
                    else -> {
                        error = null
                        pagoExitoso = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !pagoExitoso
        ) {
            Text("Confirmar pago 💰")
        }

        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver al carrito")
        }
    }

    // 🔹 Diálogo de éxito
    if (pagoExitoso) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Pago realizado con éxito") },
            text = {
                Text("¡Gracias por tu compra! Tu pedido será enviado pronto a la dirección indicada.")
            },
            confirmButton = {
                TextButton(onClick = {
                    vm.clear()
                    pagoExitoso = false
                    navController.navigate("inicio") {
                        popUpTo("inicio") { inclusive = true }
                    }
                }) {
                    Text("OK")
                }
            }
        )
    }
}
