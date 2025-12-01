package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun WishlistScreen() {

    var torneo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var ganadas by remember { mutableStateOf("") }
    var perdidas by remember { mutableStateOf("") }
    var empates by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            "Mi Registro de Torneos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = torneo,
            onValueChange = { torneo = it },
            label = { Text("Nombre del torneo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha (ej: 20/03/2026)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = ganadas,
                onValueChange = { ganadas = it },
                label = { Text("Ganadas") },
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = perdidas,
                onValueChange = { perdidas = it },
                label = { Text("Perdidas") },
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = empates,
                onValueChange = { empates = it },
                label = { Text("Empates") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = comentarios,
            onValueChange = { comentarios = it },
            label = { Text("Notas / Comentarios") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(Modifier.height(22.dp))

        Button(
            onClick = { /* No guarda nada, versión simple */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
