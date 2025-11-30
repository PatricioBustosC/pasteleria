package com.example.pasteleria.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pasteleria.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {

    val usuario by usuarioViewModel.usuarioLogueado.collectAsState()
    val lanzadorGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            usuarioViewModel.cambiarFotoPerfil(it.toString())
        }
    }

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

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable {
                        lanzadorGaleria.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            ) {
                if (usuario?.imagen != null && usuario?.imagen!!.isNotEmpty()) {
                    AsyncImage(
                        model = usuario?.imagen,
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Sin foto",
                        modifier = Modifier.size(80.dp),
                        tint = colorChocolate
                    )
                }
            }

            Text("(Toca el círculo para cambiar foto)", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = usuario?.nombre ?: "",
                onValueChange = { },
                label = { Text("Nombre") },
                readOnly = true,
                enabled = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = usuario?.email ?: "",
                onValueChange = { },
                label = { Text("Correo") },
                readOnly = true,
                enabled = false
            )

            Spacer(modifier = Modifier.height(32.dp))

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