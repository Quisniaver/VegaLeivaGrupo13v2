package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NosotrosScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Nosotros", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Tienda Geek TCG. Calidad y pasión por el TCG.")
        Divider(Modifier.padding(vertical = 12.dp))
        GalleryPickerDemo()
        Divider(Modifier.padding(vertical = 12.dp))
        LocationDemo()
    }
}