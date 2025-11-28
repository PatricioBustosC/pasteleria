package com.example.pasteleria.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // IMPORTANTE
import androidx.compose.runtime.getValue     // IMPORTANTE
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage // USAR COIL
import com.example.pasteleria.viewmodel.ProductoViewModel
import com.example.pasteleria.viewmodel.UsuarioViewModel
import com.example.pasteleria.components.TopBarUsuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    // 1. CORRECCIÓN: Usamos collectAsState para observar los cambios en vivo
    val carrito by productoViewModel.carrito.collectAsState()

    val colorBeige = Color(0xFFFFF4E6)
    val colorRosado = Color(0xFFFFC1CC)
    val colorChocolate = Color(0xFF8B4513)
    val colorCeleste = Color(0xFF8BD6E3)

    Scaffold(
        topBar = { TopBarUsuario(navController, usuarioViewModel) },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorBeige)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (carrito.isNotEmpty()) {
                    // Calculamos el total
                    val total = carrito.sumOf { it.precio * it.cantidad }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total: $${total.toInt()}",
                            fontWeight = FontWeight.Bold,
                            color = colorChocolate
                        )

                        Row {
                            Button(
                                onClick = { productoViewModel.limpiarCarrito() },
                                colors = ButtonDefaults.buttonColors(containerColor = colorRosado)
                            ) {
                                Text("Vaciar", color = colorChocolate)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { navController.navigate("resumen") },
                                colors = ButtonDefaults.buttonColors(containerColor = colorCeleste)
                            ) {
                                Text("Pagar", color = colorChocolate)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = { navController.navigate("catalogo") },
                    colors = ButtonDefaults.buttonColors(containerColor = colorRosado)
                ) {
                    Text("Volver al catálogo", color = colorChocolate)
                }
            }
        }
    ) { padding ->
        if (carrito.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorBeige)
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío 😢", color = colorChocolate)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .background(colorBeige)
                    .padding(8.dp)
            ) {
                items(carrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // 2. CORRECCIÓN: AsyncImage para la URL
                            AsyncImage(
                                model = producto.imagenUrl,
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    producto.nombre,
                                    fontWeight = FontWeight.Bold,
                                    color = colorChocolate
                                )
                                Text(
                                    "$${producto.precio}",
                                    color = Color.Gray
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = {
                                        productoViewModel.disminuirCantidad(producto)
                                    }) { Text("➖") }

                                    Text("${producto.cantidad}", color = colorChocolate)

                                    IconButton(onClick = {
                                        productoViewModel.aumentarCantidad(producto)
                                    }) { Text("➕") }
                                }
                            }

                            IconButton(onClick = {
                                productoViewModel.eliminarDelCarrito(producto)
                            }) {
                                Text("🗑️") // Cambié el icono por un basurero
                            }
                        }
                    }
                }
            }
        }
    }
}