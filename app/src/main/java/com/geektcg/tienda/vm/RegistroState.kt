package com.geektcg.tienda.vm

data class RegistroState(
    val nombre: String = "",
    val email: String = "",
    val pass1: String = "",
    val pass2: String = "",

    val errorNombre: String? = null,
    val errorEmail: String? = null,
    val errorPass1: String? = null,
    val errorPass2: String? = null,

    val loading: Boolean = false,
    val valido: Boolean = false
)