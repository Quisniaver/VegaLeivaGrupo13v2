package com.geektcg.tienda.ui

import androidx.annotation.DrawableRes

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    @DrawableRes val imagen: Int
)