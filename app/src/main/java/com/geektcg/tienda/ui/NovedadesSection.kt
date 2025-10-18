package com.geektcg.tienda.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun NovedadesSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        repeat(2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Nueva expansión disponible", fontWeight = FontWeight.Bold)
                    Text(
                        "Descubre las nuevas cartas de edición limitada.",
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}