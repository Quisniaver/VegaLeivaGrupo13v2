package com.geektcg.tienda.vm

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object UsuarioStorage {
    private const val FILE_NAME = "usuarios.json"

    private val gson = Gson()

    fun obtenerUsuarios(context: Context): List<Usuario> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) {
            // 🔹 Crear admin por defecto
            val admin = Usuario(
                nombre = "Administrador",
                email = "admin@geektcg.cl",
                password = "admin123",
                isAdmin = true
            )
            val adminList = listOf(admin)
            file.writeText(gson.toJson(adminList))
            return adminList
        }

        val type = object : TypeToken<List<Usuario>>() {}.type
        return gson.fromJson(file.readText(), type) ?: emptyList()
    }

    fun guardarUsuario(context: Context, usuario: Usuario) {
        val lista = obtenerUsuarios(context).toMutableList()

        // Evita duplicados por email
        if (lista.any { it.email == usuario.email }) return

        lista.add(usuario)
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(gson.toJson(lista))
    }

    fun eliminarUsuario(context: Context, email: String) {
        val lista = obtenerUsuarios(context).toMutableList()
        val nuevaLista = lista.filterNot { it.email == email }
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(gson.toJson(nuevaLista))
    }
}


