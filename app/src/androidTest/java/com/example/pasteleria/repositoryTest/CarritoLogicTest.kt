package com.example.pasteleria.repositoryTest

import com.example.pasteleria.model.Producto
import org.junit.Test
import org.junit.Assert.*

class CarritoLogicTest {

    @Test
    fun productoSeCreaConValoresCorrectos() {
        val pastel = Producto(
            id = 1,
            nombre = "Prueba",
            descripcion = "Desc",
            precio = 5000.0,
            imagenUrl = "url",
            cantidad = 1
        )


        assertEquals("Prueba", pastel.nombre)
        assertEquals(5000.0, pastel.precio, 0.0)
    }

    @Test
    fun calculoDeTotalDelCarritoEsCorrecto() {

        val producto1 = Producto(id = 1, nombre = "A", descripcion = "D", precio = 1000.0, imagenUrl = "url", cantidad = 2)
        val producto2 = Producto(id = 2, nombre = "B", descripcion = "D", precio = 500.0, imagenUrl = "url", cantidad = 1)

        val listaCarrito = listOf(producto1, producto2)


        val total = listaCarrito.sumOf { it.precio * (it.cantidad ?: 1) }

        assertEquals(2500.0, total, 0.0)
    }
}