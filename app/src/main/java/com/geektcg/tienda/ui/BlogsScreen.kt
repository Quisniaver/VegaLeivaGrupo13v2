package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlogsScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Blogs", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Aquí irán entradas de blog.")
    }
}