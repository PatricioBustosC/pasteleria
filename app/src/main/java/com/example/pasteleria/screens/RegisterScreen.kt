package com.example.pasteleria.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.UsuarioViewModel
import com.example.pasteleria.components.CustomTextoCampo
import com.example.pasteleria.model.Usuario

@Composable
fun RegisterScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorMensaje by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextoCampo(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
        Spacer(modifier = Modifier.height(8.dp))

        CustomTextoCampo(value = email, onValueChange = { email = it }, label = "Correo electrónico")
        Spacer(modifier = Modifier.height(8.dp))

        CustomTextoCampo(value = password, onValueChange = { password = it }, label = "Contraseña")

        if (errorMensaje != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMensaje!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isBlank() || email.isBlank() || password.isBlank()) {
                errorMensaje = "Por favor complete todos los campos"
            } else {
                val nuevoUsuario = Usuario(
                    id = (1..10000).random(),
                    nombre = nombre,
                    email = email,
                    password = password,
                    imagen = null
                )

                val registroExitoso = usuarioViewModel.registrar(nuevoUsuario)

                if (registroExitoso) {
                    navController.navigate("login") {
                        popUpTo("registro") { inclusive = true }
                    }
                } else {
                    errorMensaje = "El correo ya está registrado"
                }
            }
        }) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate("login") }) {
            Text("Volver al inicio de sesión")
        }
    }
}