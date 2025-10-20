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
import androidx.navigation.NavController
import com.geektcg.tienda.R
import com.geektcg.tienda.Screen

import com.geektcg.tienda.ui.*

@Composable
fun InicioScreen(navController: NavController) {

    // --- SOLUCIÓN PASO 2: CREAR LOS DATOS Y USAR IMÁGENES QUE EXISTAN ---
    // Creamos la lista de datos.
    // NOTA: He cambiado R.drawable.torneo y R.drawable.accesorios por imágenes
    // que probablemente sí tienes. ¡Asegúrate de que 'novedades1' y 'logo' existan!
    val listaDeNovedades = listOf(
        Novedad(1, "¡Vuelve la Megaevolución!", "¡Los Pokémon ex Megaevolución son aún más poderosos y entregan 3 cartas de Premio cuando quedan Fuera de Combate!", R.drawable.novedades1),
        Novedad(2, "Booster Packs", "Yu-Gi-Oh!: Nuevo lanzamiento: 26-02-2026 /n. Reserva ya .", R.drawable.novedades2), // Usando 'logo' como placeholder
        Novedad(3, "Mitos y leyenda", "MYL: Preventa Primera Era", R.drawable.myl) // Usando 'novedades1' como placeholder
    )

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
            CardsSection() // Asegúrate de que CardsSection también esté corregido
        }

        item {
            SectionTitle("Novedades")

            // --- SOLUCIÓN PASO 3: LLAMAR A LA FUNCIÓN CORRECTAMENTE ---
            // Ahora esta llamada funciona porque:
            // 1. El 'import' le dice al compilador dónde está NovedadesSection.
            // 2. Le pasamos la lista de novedades que creamos arriba.
            // 3. Le pasamos la acción de navegación.
            NovedadesSection(
                novedades = listaDeNovedades,
                onProductClick = { novedadId ->
                    navController.navigate(Screen.Detalle.withId(novedadId))
                }


            )
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