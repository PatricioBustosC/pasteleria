package com.example.pasteleria.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.UsuarioViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUsuario(navController: NavController, usuarioViewModel: UsuarioViewModel) {

    val usuario by usuarioViewModel.usuarioLogueado.collectAsState()

    val colorChocolate = Color(0xFF8B4513)
    val colorBeige = Color(0xFFFFF4E6)

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Bienvenido, ${usuario?.nombre ?: "Invitado"}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorChocolate
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorBeige
        ),
        actions = {
            IconButton(onClick = { navController.navigate(route = "perfil") }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Perfil",
                    tint = colorChocolate
                )
            }
        }
    )
}