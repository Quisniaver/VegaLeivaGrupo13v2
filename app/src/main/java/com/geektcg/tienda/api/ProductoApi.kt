package com.geektcg.tienda.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Query

data class ProductoRemote(
    val id: Int = 0,
    val nombre: String,
    val precio: Int,
    val imagen: String?,
    val categoria: String
)

interface ProductoApi {

    // 🔹 Obtener productos (todos o filtrados)
    @GET("api/productos")
    suspend fun listarProductos(
        @Query("categoria") categoria: String? = null
    ): List<ProductoRemote>

    // 🔹 Crear un producto
    @POST("api/productos/crear")
    suspend fun crearProducto(
        @Body producto: ProductoRemote
    ): ProductoRemote

    // 🔹 Eliminar un producto por ID
    @DELETE("api/productos/{id}")
    suspend fun eliminarProducto(
        @Path("id") id: Int
    )

    // 🔹 Cargar lista completa (solo si quieres añadir varios a la vez)
    @POST("api/productos/cargarLote")
    suspend fun cargarLote(
        @Body productos: List<ProductoRemote>
    )
}
