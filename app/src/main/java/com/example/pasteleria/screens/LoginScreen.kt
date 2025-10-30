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
    var error by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (usuarioViewModel.login(email, password)) {
                navController.navigate("catalogo")
            } else {
                error = "Credenciales incorrectas"
            }
        }) {
            Text("Entrar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("registro") }) {
            Text("Registrarse")
        }
        if (error.isNotEmpty()) {
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}
