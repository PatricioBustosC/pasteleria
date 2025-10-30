package com.example.pasteleria.repository

import com.example.pasteleria.model.Producto
import com.example.pasteleria.R

class ProductoRepository {
    fun obtenerProductos(): List<Producto> {
        return listOf(
            Producto(1, "Pastel de Chocolate", "Pastel húmedo con cobertura de cacao", 5000.0, R.drawable.ic_launcher_foreground),
            Producto(2, "Cheesecake", "Tarta de queso con base de galleta", 4500.0, R.drawable.ic_launcher_foreground),
            Producto(3, "Pie de Limón", "Postre fresco con merengue", 4000.0, R.drawable.ic_launcher_foreground)
        )
    }
}
