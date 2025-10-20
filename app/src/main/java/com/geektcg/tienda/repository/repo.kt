package com.geektcg.tienda.data

import com.geektcg.tienda.R

// Modelo de datos para un producto
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: Int,     // Recurso drawable
    val categoria: String // Nueva propiedad
)

// Repositorio simple de productos (mock local)
object Repo {
    val productos = listOf(
        // Pokémon
        Producto(1, "Mega-Lucario ex", 20000, R.drawable.novedades1, "pokemon"),

        // Yu-Gi-Oh!
        Producto(2, "Yu-Gi-Oh! - Booster Packs", 25990, R.drawable.novedades2, "yugioh"),

        // Mitos y Leyendas
        Producto(3, "PREVENTA 3 RACIAL PRIMERA ERA 2025 - CABALLERO", 74990, R.drawable.myl, "myl"),

        // Accesorios
        Producto(4, "Carta Oscura", 7990, R.drawable.carta5, "accesorios")
    )
}

