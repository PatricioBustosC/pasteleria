package com.example.pasteleria.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.UsuarioViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {

    val usuario by usuarioViewModel.usuarioLogueado.collectAsState()

    var nombre by remember { mutableStateOf(usuario?.nombre ?: "") }
    var email by remember { mutableStateOf(usuario?.email ?: "") }
    var password by remember { mutableStateOf(usuario?.password ?: "") }

    val colorChocolate = Color(0xFF8B4513)
    val colorRosado = Color(0xFFFFC1CC)
    val colorBeige = Color(0xFFFFF4E6)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Perfil", color = colorChocolate) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colorBeige)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Imagen de perfil",
                modifier = Modifier.size(100.dp),
                tint = colorChocolate
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                readOnly = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                readOnly = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                readOnly = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    usuarioViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("catalogo") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorRosado)
            ) {
                Text("Cerrar Sesión", color = colorChocolate)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("catalogo") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("Volver", color = Color.Black)
            }
        }
    }
}