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
fun RegisterScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextoCampo(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextoCampo(value = email, onValueChange = { email = it }, label = "Correo")
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextoCampo(value = password, onValueChange = { password = it }, label = "Contraseña")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            usuarioViewModel.registrar(nombre, email, password)
            navController.navigate("login")
        }) {
            Text("Registrar")
        }
    }
}
