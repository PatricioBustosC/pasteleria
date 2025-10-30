package com.example.pasteleria.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavController, productoViewModel: ProductoViewModel) {
    val carrito = productoViewModel.carrito

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carrito de Compras") }
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEFD5))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (carrito.isNotEmpty()) {
                    val total = carrito.sumOf { it.precio * (it.cantidad ?: 1) }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total: $${total.toInt()}",
                            fontWeight = FontWeight.Bold
                        )

                        Row {
                            Button(
                                onClick = { productoViewModel.limpiarCarrito() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC1CC))
                            ) {
                                Text("Vaciar")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { navController.navigate("resumen") },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BD6E3))
                            ) {
                                Text("Pagar")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Este botón siempre aparece, incluso con el carrito vacío
                Button(
                    onClick = { navController.navigate("catalogo") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEAD1DC))
                ) {
                    Text("Volver al catálogo")
                }
            }
        }
    ) { padding ->
        if (carrito.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío 😢")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(8.dp)
            ) {
                items(carrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = producto.imagen),
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(producto.nombre, fontWeight = FontWeight.Bold)
                                Text("$${producto.precio}", color = Color.Gray)

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = {
                                        productoViewModel.disminuirCantidad(producto)
                                    }) { Text("➖") }

                                    Text("${producto.cantidad ?: 1}")

                                    IconButton(onClick = {
                                        productoViewModel.aumentarCantidad(producto)
                                    }) { Text("➕") }
                                }
                            }

                            IconButton(onClick = {
                                productoViewModel.eliminarDelCarrito(producto)
                            }) {
                                Text("🥐")
                            }
                        }
                    }
                }
            }
        }
    }
}
