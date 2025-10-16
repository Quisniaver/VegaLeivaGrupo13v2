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
        Producto(1, "Carta Legendaria Azul", 4990, R.drawable.carta1),
        Producto(2, "Carta Dragón Rojo", 5990, R.drawable.carta2),
        Producto(3, "Carta Hielo Épica", 6990, R.drawable.carta3),
        Producto(4, "Carta Oscura", 7990, R.drawable.carta5)
    )
}
