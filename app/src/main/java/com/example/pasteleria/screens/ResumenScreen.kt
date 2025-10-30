package com.example.pasteleria.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val carrito = productoViewModel.carrito
    val total = carrito.sumOf { it.precio * (it.cantidad ?: 1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Resumen del Pedido") }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    productoViewModel.limpiarCarrito()
                    navController.navigate("catalogo") {
                        popUpTo("catalogo") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Finalizar y volver al catálogo")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Gracias por tu compra",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Detalles del pedido:", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(carrito) { producto ->
                    Text("${producto.nombre} x${producto.cantidad} - $${producto.precio * (producto.cantidad ?: 1)}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total pagado: $${total.toInt()}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
