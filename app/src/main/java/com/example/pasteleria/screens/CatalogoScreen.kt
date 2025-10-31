package com.example.pasteleria.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.ProductoViewModel
import com.example.pasteleria.viewmodel.UsuarioViewModel
import com.example.pasteleria.components.TopBarUsuario
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val colorCrema = Color(0xFFFFF4E6)
    val colorChocolate = Color(0xFF8B4513)
    val colorRosado = Color(0xFFFFC1CC)

    Scaffold(
        topBar = {
            TopBarUsuario(navController, usuarioViewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("carrito") },
                containerColor = colorRosado
            ) {
                Text("🛒")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorCrema)
                .padding(padding)
                .padding(12.dp)
        ) {
            items(productoViewModel.productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
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
                                .size(100.dp)
                                .padding(end = 16.dp)
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = producto.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                color = colorChocolate
                            )
                            Text(
                                text = producto.descripcion,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$${producto.precio}",
                                style = MaterialTheme.typography.titleSmall,
                                color = colorRosado
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    productoViewModel.agregarAlCarrito(producto)
                                    scope.launch {
                                        snackbarHostState.showSnackbar("${producto.nombre} agregado al carrito")
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = colorRosado),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Agregar al carrito", color = colorChocolate)
                            }
                        }
                    }
                }
            }
        }
    }
}
