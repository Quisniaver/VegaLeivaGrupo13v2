package com.geektcg.tienda.ui.theme



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes // Clase importada
import androidx.compose.ui.unit.dp

// ¡Esta es la VARIABLE que debe ser referenciada en Theme.kt!
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)
