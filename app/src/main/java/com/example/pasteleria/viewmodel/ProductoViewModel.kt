package com.example.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleria.model.Producto
import com.example.pasteleria.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {

    // Lista de productos del catálogo (viene de Internet)
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> get() = _productos

    // Lista del carrito de compras (Local)
    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> get() = _carrito

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                // Llamada a la API
                _productos.value = RetrofitInstance.api.obtenerProductos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // --- LÓGICA DEL CARRITO ---

    fun agregarAlCarrito(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        val existente = listaActual.find { it.id == producto.id }

        if (existente != null) {
            existente.cantidad += 1
        } else {
            // Copiamos el producto para no alterar el del catálogo original
            listaActual.add(producto.copy(cantidad = 1))
        }
        _carrito.value = listaActual
    }

    fun aumentarCantidad(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        val item = listaActual.find { it.id == producto.id }
        item?.let {
            it.cantidad += 1
            _carrito.value = listaActual // Actualizamos estado
        }
    }

    fun disminuirCantidad(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        val item = listaActual.find { it.id == producto.id }
        item?.let {
            if (it.cantidad > 1) {
                it.cantidad -= 1
            } else {
                listaActual.remove(it)
            }
            _carrito.value = listaActual
        }
    }

    fun eliminarDelCarrito(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        listaActual.removeIf { it.id == producto.id }
        _carrito.value = listaActual
    }

    fun limpiarCarrito() {
        _carrito.value = emptyList()
    }
}