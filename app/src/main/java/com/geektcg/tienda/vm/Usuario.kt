package com.geektcg.tienda.vm

data class Usuario(
    val nombre: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean = false
)
