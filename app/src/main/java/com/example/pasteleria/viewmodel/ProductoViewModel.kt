package com.example.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleria.model.Producto
import com.example.pasteleria.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> get() = _productos

    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> get() = _carrito

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                // Aquí obtenemos la lista "limpia" del servidor
                _productos.value = RetrofitInstance.api.obtenerProductos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun agregarAlCarrito(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        // Buscamos si ya está en el carrito
        val index = listaActual.indexOfFirst { it.id == producto.id }

        if (index != -1) {
            // Si existe, hacemos una COPIA con cantidad + 1
            val itemExistente = listaActual[index]
            val itemActualizado = itemExistente.copy(cantidad = itemExistente.cantidad + 1)
            listaActual[index] = itemActualizado
        } else {
            // Si no existe, lo agregamos con cantidad 1
            listaActual.add(producto.copy(cantidad = 1))
        }
        _carrito.value = listaActual
    }

    fun aumentarCantidad(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        val index = listaActual.indexOfFirst { it.id == producto.id }

        if (index != -1) {
            val item = listaActual[index]
            // ¡ESTO ES LO IMPORTANTE! Usamos .copy()
            val itemActualizado = item.copy(cantidad = item.cantidad + 1)

            // Reemplazamos el viejo por el nuevo en la lista
            listaActual[index] = itemActualizado

            // Avisamos al estado que la lista cambió
            _carrito.value = listaActual
        }
    }

    fun disminuirCantidad(producto: Producto) {
        val listaActual = _carrito.value.toMutableList()
        val index = listaActual.indexOfFirst { it.id == producto.id }

        if (index != -1) {
            val item = listaActual[index]
            if (item.cantidad > 1) {
                // Si es mayor a 1, restamos usando copia
                val itemActualizado = item.copy(cantidad = item.cantidad - 1)
                listaActual[index] = itemActualizado
            } else {
                // Si es 1 y restamos, lo borramos
                listaActual.removeAt(index)
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