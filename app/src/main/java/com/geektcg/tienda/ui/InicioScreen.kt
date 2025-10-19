package com.geektcg.tienda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geektcg.tienda.R
// Si CardsSection y NovedadesSection están en el mismo paquete, no necesitas estas importaciones explícitas.
// Si están en archivos separados pero en 'com.geektcg.tienda.ui', las quitamos para limpiar.
// import com.geektcg.tienda.ui.CardsSection
// import com.geektcg.tienda.ui.NovedadesSection

@Composable
fun InicioScreen(
    // ❗ CORRECCIÓN: Agregar el parámetro de navegación requerido por MainActivity
    onVerProducto: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Hero
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "GEEK TCG - Trading Card Game",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "GEEK TCG",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "TRADING CARD GAME",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 18.sp
                    )
                }
            }
        }

        item {
            SectionTitle("Características")
            // Asumiendo que CardsSection no tiene navegación aquí.
            CardsSection()
        }

        item {
            SectionTitle("Novedades")
            // ❗ CORRECCIÓN: Pasar el callback a NovedadesSection para que pueda navegar
            NovedadesSection(onProductClick = onVerProducto)
        }

        item { Spacer(Modifier.height(8.dp)) }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    )
}