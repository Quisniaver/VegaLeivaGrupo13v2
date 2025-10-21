package com.geektcg.tienda.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BlogsScreen() {
    val cardBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6A11CB), // morado intenso
            Color(0xFF2575FC)  // azul brillante
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Blogs TCG",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        // --- Noticia 1 ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                Modifier
                    .background(cardBrush)
                    .padding(16.dp)
            ) {
                Text(
                    "Nueva expansión de Disney Lorcana: “Whispers in the Well”",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Disney Lorcana lanzará su décimo capítulo “Whispers in the Well”, "
                            + "introduciendo la mecánica “Boost”, que mejora las habilidades "
                            + "al colocar cartas bajo otras, aportando más estrategia al juego.",
                    color = Color(0xFFECEBFF)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Fuente: cadenaser.com",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFBBDEFB))
                )
            }
        }

        // --- Noticia 2 ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                Modifier
                    .background(cardBrush)
                    .padding(16.dp)
            ) {
                Text(
                    "Próxima expansión de Pokémon TCG: “Fuegos Fantasmales”",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "La expansión “Fuegos Fantasmales” llega el 14 de noviembre de 2025 con "
                            + "cartas que podrían cambiar el meta, como Empoleon ex de 320 PS, "
                            + "mezclando control y daño en una nueva dinámica competitiva.",
                    color = Color(0xFFECEBFF)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Fuente: as.com",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFBBDEFB))
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            "Mantente atento a más novedades del mundo TCG 🔥",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
