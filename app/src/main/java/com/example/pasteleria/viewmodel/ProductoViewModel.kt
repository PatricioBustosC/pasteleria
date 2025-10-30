package com.example.pasteleria.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pasteleria.model.Producto
import com.example.pasteleria.R

class ProductoViewModel : ViewModel() {
    val productos = listOf(
        Producto(1, "Torta de Chocolate", "Deliciosa torta con cobertura", 15000.0, R.drawable.torta_chocolate),
        Producto(2, "Cheesecake Frutilla", "Con mermelada natural", 18000.0, R.drawable.cheesecake_frutilla),
        Producto(3, "Cupcake Vainilla", "Decorado con crema suave", 2500.0, R.drawable.cupcake_vainilla)
    )

    private val _carrito = mutableStateListOf<Producto>()
    val carrito: List<Producto> get() = _carrito

    fun agregarAlCarrito(producto: Producto) {
        val existente = _carrito.find { it.id == producto.id }
        if (existente != null) {
            existente.cantidad = (existente.cantidad ?: 1) + 1
        } else {
            _carrito.add(producto.copy(cantidad = 1))
        }
    }

    fun eliminarDelCarrito(producto: Producto) {
        _carrito.remove(producto)
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }

    fun aumentarCantidad(producto: Producto) {
        val index = _carrito.indexOfFirst { it.id == producto.id }
        if (index != -1) {
            val actualizado = _carrito[index].copy(cantidad = (_carrito[index].cantidad ?: 1) + 1)
            _carrito[index] = actualizado
        }
    }

    fun disminuirCantidad(producto: Producto) {
        val index = _carrito.indexOfFirst { it.id == producto.id }
        if (index != -1) {
            val actual = _carrito[index].cantidad ?: 1
            if (actual > 1) {
                val actualizado = _carrito[index].copy(cantidad = actual - 1)
                _carrito[index] = actualizado
            } else {
                _carrito.removeAt(index)
            }
        }
    }

}
