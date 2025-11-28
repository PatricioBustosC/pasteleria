package com.example.pasteleria.network

import com.example.pasteleria.model.Producto
import retrofit2.http.GET

interface ProductoApiService {

    @GET("/productos")
    suspend fun obtenerProductos(): List<Producto>
}
