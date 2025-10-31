package com.example.pasteleria.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.ProductoViewModel
import com.example.pasteleria.model.Producto
import kotlin.random.Random
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun ResumenScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val carrito = productoViewModel.carrito
    val total = carrito.sumOf { it.precio * (it.cantidad ?: 1) }

    Box(modifier = Modifier.fillMaxSize()) {
        ConfetiAnimacion()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = " ¡Compra realizada con éxito! ",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF8B4513) // chocolate suave
            )

            Spacer(modifier = Modifier.height(16.dp))

            carrito.forEach { producto ->
                Text(
                    text = "${producto.nombre} x${producto.cantidad ?: 1} - $${producto.precio * (producto.cantidad ?: 1)}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: $${total.toInt()}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6B4226)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    productoViewModel.limpiarCarrito()
                    navController.navigate("catalogo")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC1CC))
            ) {
                Text("Volver al catálogo")
            }
        }
    }
}


@Composable
fun ResumenItem(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("${producto.nombre} x${producto.cantidad}")
            Text("$${producto.precio * (producto.cantidad ?: 1)}")
        }
    }
}

@Composable
fun ConfetiAnimacion() {
    val confeti = remember { List(50) { Random.nextFloat() to Random.nextFloat() } }

    Canvas(modifier = Modifier.fillMaxSize()) {
        confeti.forEach { (x, y) ->
            drawCircle(
                color = Color(0xFFFFC1CC), // Rosado crema pastel
                radius = 6f,
                center = androidx.compose.ui.geometry.Offset(
                    x = x * size.width,
                    y = y * size.height
                )
            )
        }
    }
}


