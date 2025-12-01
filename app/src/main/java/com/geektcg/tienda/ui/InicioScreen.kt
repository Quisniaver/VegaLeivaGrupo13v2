package com.geektcg.tienda.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geektcg.tienda.R
import com.geektcg.tienda.Screen

@Composable
fun InicioScreen(navController: NavController) {

    val listaDeNovedades = listOf(
        Novedad(
            1,
            "¡Vuelve la Megaevolución!",
            "¡Los Pokémon ex Megaevolución son aún más poderosos y entregan 3 cartas de Premio!",
            R.drawable.pokemon1
        ),
        Novedad(
            5,
            "Booster Packs",
            "Yu-Gi-Oh!: Nuevo lanzamiento: 26-02-2026. Reserva ya.",
            R.drawable.yugioh1
        ),
        Novedad(
            9,
            "Mitos y Leyenda",
            "MYL: Preventa Primera Era",
            R.drawable.myl1
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        // 🔹 Íconos principales
        item {
            SectionTitle("Explorar")
            CardsSection(navController)

        }

        // 🔹 Novedades
        item {
            SectionTitle("Novedades")
            NovedadesSection(
                novedades = listaDeNovedades,
                onProductClick = { id ->
                    navController.navigate(Screen.Detalle.withId(id))
                }
            )
        }

        item { Spacer(Modifier.height(12.dp)) }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    )
}