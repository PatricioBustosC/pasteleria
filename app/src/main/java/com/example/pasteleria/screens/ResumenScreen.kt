package com.example.pasteleria.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.model.Producto
import com.example.pasteleria.viewmodel.ProductoViewModel
import kotlin.random.Random

@Composable
fun ResumenScreen(navController: NavController, productoViewModel: ProductoViewModel) {

    val carrito by productoViewModel.carrito.collectAsState()

    val total = carrito.sumOf { it.precio * it.cantidad }

    Box(modifier = Modifier.fillMaxSize()) {
        ConfetiAnimacion()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Compra realizada con éxito! 🎉",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF8B4513)
            )

            Spacer(modifier = Modifier.height(24.dp))

            carrito.forEach { producto ->
                Text(
                    text = "${producto.nombre} x${producto.cantidad} = $${producto.precio * producto.cantidad}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Divider(color = Color.LightGray)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total Pagado: $${total.toInt()}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6B4226)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    productoViewModel.limpiarCarrito()
                    navController.navigate("catalogo") {
                        popUpTo("catalogo") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC1CC))
            ) {
                Text("Volver al catálogo", color = Color(0xFF8B4513))
            }
        }
    }
}

@Composable
fun ConfetiAnimacion() {
    val confeti = remember { List(50) { Random.nextFloat() to Random.nextFloat() } }
    val colores = listOf(Color(0xFFFFC1CC), Color(0xFF8BD6E3), Color(0xFFFFF4E6))

    Canvas(modifier = Modifier.fillMaxSize()) {
        confeti.forEach { (x, y) ->
            drawCircle(
                color = colores.random(),
                radius = 8f,
                center = androidx.compose.ui.geometry.Offset(
                    x = x * size.width,
                    y = y * size.height
                )
            )
        }
    }
}