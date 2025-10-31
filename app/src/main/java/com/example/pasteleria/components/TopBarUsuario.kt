package com.example.pasteleria.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.pasteleria.viewmodel.UsuarioViewModel
import com.example.pasteleria.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUsuario(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val usuario = usuarioViewModel.usuarioActual.value
    val colorChocolate = Color(0xFF8B4513)
    val colorBeige = Color(0xFFFFF4E6)

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Bienvenido, ${usuario?.nombre ?: "Usuario"}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorChocolate
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorBeige
        ),
        actions = {
            IconButton(onClick = { navController.navigate("perfil") }) {
                Icon(
                    painter = painterResource(
                        id = usuario?.imagen ?: R.drawable.ic_persona
                    ),
                    contentDescription = "Perfil",
                    tint = if (usuario?.imagen != null) Color.Unspecified else Color.Black
                )
            }
        }
    )
}
