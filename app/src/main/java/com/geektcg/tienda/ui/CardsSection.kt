package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 🔹 Estructura de datos para cada card
data class Feature(
    val titulo: String,
    val subtitulo: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)

@Composable
fun CardsSection() {
    // 🔹 Lista de cards distintas (podrías mover esto a un ViewModel después)
    val features = listOf(
        Feature(
            titulo = "Cartas Exclusivas",
            subtitulo = "Colecciona y amplía tu mazo",
            icon = Icons.Filled.Style,
            color = Color(0xFFD63384) // Rosa TCG
        ),
        Feature(
            titulo = "Compra Segura",
            subtitulo = "Adquiere tus sobres y accesorios",
            icon = Icons.Filled.ShoppingCart,
            color = Color(0xFF8A3A6A) // Púrpura TCG
        ),
        Feature(
            titulo = "Juega Online",
            subtitulo = "Desafía a otros duelistas",
            icon = Icons.Filled.SportsEsports,
            color = Color(0xFF007BFF) // Azul brillante
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        features.forEach { feature ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = feature.icon,
                        contentDescription = feature.titulo,
                        tint = feature.color,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = feature.titulo,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = feature.subtitulo,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}
