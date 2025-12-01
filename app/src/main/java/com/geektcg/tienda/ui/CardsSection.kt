package com.geektcg.tienda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.geektcg.tienda.R

@Composable
fun CardsSection(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Lista de tarjetas
        val cards = listOf(
            Triple("Productos Exclusivos", "Encuentra cartas únicas.", R.drawable.icon3),
            Triple("Torneos", "Participa y gana premios.", R.drawable.icon2),
            Triple("Comunidad", "Únete y comparte tu pasión.", R.drawable.icon1)
        )

        cards.forEach { (titulo, descripcion, icono) ->

            // 🔥 Hacemos clickable SOLO la tarjeta de Torneos
            val modifier = if (titulo == "Torneos") {
                Modifier
                    .weight(1f)
                    .height(170.dp)
                    .clickable {
                        navController.navigate("torneos")  // 👈 Navega a la pantalla
                    }
            } else {
                Modifier
                    .weight(1f)
                    .height(170.dp)
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = icono),
                        contentDescription = titulo,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = titulo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = descripcion,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
