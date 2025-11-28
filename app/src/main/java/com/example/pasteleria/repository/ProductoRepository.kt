package com.example.pasteleria.repository

import com.example.pasteleria.model.Producto

class ProductoRepository {

    fun obtenerProductosLocales(): List<Producto> {
        return listOf(
            Producto(
                id = 1,
                nombre = "Pastel de Chocolate",
                descripcion = "Pastel húmedo con cobertura",
                precio = 5000.0,
                imagenUrl = "https://placehold.co/600x400"
            ),
            Producto(
                id = 2,
                nombre = "Cheesecake",
                descripcion = "Tarta de queso con base",
                precio = 4500.0,
                imagenUrl = "https://placehold.co/600x400"
            ),
            Producto(
                id = 3,
                nombre = "Pie de Limón",
                descripcion = "Postre fresco con merengue",
                precio = 4000.0,
                imagenUrl = "https://placehold.co/600x400"
            )
        )
    }
}