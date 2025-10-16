package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdmHomeScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Panel Admin (placeholder)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Accesos a gestión de productos, pedidos, etc.")
    }
}