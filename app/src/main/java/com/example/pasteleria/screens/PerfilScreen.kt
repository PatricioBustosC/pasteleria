package com.example.pasteleria.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.UsuarioViewModel
import com.example.pasteleria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val usuario = usuarioViewModel.usuarioActual.value
    var nombre by remember { mutableStateOf(usuario?.nombre ?: "") }
    var email by remember { mutableStateOf(usuario?.email ?: "") }
    var password by remember { mutableStateOf(usuario?.password ?: "") }

    val colorChocolate = Color(0xFF8B4513)
    val colorRosado = Color(0xFFFFC1CC)
    val colorBeige = Color(0xFFFFF4E6)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Perfil", color = colorChocolate) },
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
            Image(
                painter = painterResource(id = usuario?.imagen ?: R.drawable.ic_persona),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    usuarioViewModel.usuarioActual.value =
                        usuario?.copy(nombre = nombre, email = email, password = password)
                    navController.navigate("catalogo")
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorRosado)
            ) {
                Text("Guardar cambios", color = colorChocolate)
            }
        }
    }
}
