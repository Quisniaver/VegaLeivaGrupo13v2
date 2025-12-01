package com.geektcg.tienda.vm

import android.util.Log
import com.geektcg.tienda.api.ApiClient
import com.geektcg.tienda.api.UsuarioApi

object UsuarioStorage {

    private val api: UsuarioApi by lazy {
        ApiClient.retrofit.create(UsuarioApi::class.java)
    }

    suspend fun obtenerUsuariosRemotos(): List<Usuario> {
        return try {
            api.obtenerUsuarios()
        } catch (e: Exception) {
            Log.e("UsuarioStorage", "Error obteniendo usuarios del backend", e)
            emptyList()
        }
    }

    suspend fun crearUsuario(usuario: Usuario): Usuario? {
        return try {
            api.crearUsuario(usuario)
        } catch (e: Exception) {
            Log.e("UsuarioStorage", "Error creando usuario en backend", e)
            null
        }
    }

    suspend fun eliminarUsuario(email: String): Boolean {
        return try {
            api.eliminarUsuario(email)
            true
        } catch (e: Exception) {
            Log.e("UsuarioStorage", "Error eliminando usuario en backend", e)
            false
        }
    }
}
