package com.geektcg.tienda.data

import com.geektcg.tienda.R

// Modelo de datos para un producto
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: Int // ✅ Int porque apunta a un recurso drawable
)

// Repositorio simple de productos (mock local)
object Repo {
    val productos = listOf(
        Producto(1, "Mega-Lucario ex", 20000, R.drawable.novedades1),
        Producto(2, "Yu-Gi-Oh!- Booster Packs", 25990, R.drawable.novedades2),
        Producto(3, "PREVENTA 3 RACIAL PRIMERA ERA 2025 - CABALLERO", 74990, R.drawable.myl),
        Producto(4, "Carta Oscura", 7990, R.drawable.carta5)
    )
}
