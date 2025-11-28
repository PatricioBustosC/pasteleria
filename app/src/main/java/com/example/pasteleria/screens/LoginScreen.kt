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

@Composable
fun LoginScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Variable local para mostrar errores en la pantalla
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextoCampo(value = email, onValueChange = { email = it }, label = "Correo")

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextoCampo(value = password, onValueChange = { password = it }, label = "Contraseña")

        // AQUÍ MOSTRAMOS EL ERROR (SI EXISTE)
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
            // 1. VALIDACIÓN: Revisamos si los campos están vacíos
            if (email.isBlank() || password.isBlank()) {
                errorMensaje = "Por favor llene todos los campos"
            } else {
                // 2. LÓGICA: Llamamos al login del ViewModel
                val loginExitoso = usuarioViewModel.login(email, password)

                if (loginExitoso) {
                    // Si el login funciona, navegamos
                    errorMensaje = null
                    navController.navigate("catalogo") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    // Si el login falla, mostramos error
                    errorMensaje = "Correo o contraseña incorrectos"
                }
            }
        }) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate("registro") }) {
            Text("Registrarse")
        }
    }
}