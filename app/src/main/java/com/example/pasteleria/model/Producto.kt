package com.example.pasteleria.model

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: Int,
    var cantidad: Int? = 1
)
