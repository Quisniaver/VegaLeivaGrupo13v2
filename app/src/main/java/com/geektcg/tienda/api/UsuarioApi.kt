package com.geektcg.tienda.api

import com.geektcg.tienda.vm.Usuario
import retrofit2.http.*

interface UsuarioApi {

    // Obtener todos los usuarios
    @GET("api/usuarios")
    suspend fun obtenerUsuarios(): List<Usuario>

    // Crear usuario (esta es la ruta REAL del backend)
    @POST("api/usuarios")
    suspend fun crearUsuario(
        @Body usuario: Usuario
    ): Usuario

    // Eliminar usuario por email
    @DELETE("api/usuarios/{email}")
    suspend fun eliminarUsuario(
        @Path("email") email: String
    )
}
