package com.example.pasteleria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pasteleria.screens.*
import com.example.pasteleria.viewmodel.ProductoViewModel
import com.example.pasteleria.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel = UsuarioViewModel()
    val productoViewModel = ProductoViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, usuarioViewModel)
        }
        composable("registro") {
            RegisterScreen(navController, usuarioViewModel)
        }
        composable("catalogo") {
            CatalogoScreen(navController, productoViewModel)
        }
        composable("carrito") {
            CarritoScreen(navController, productoViewModel)
        }
        composable("resumen") {
            ResumenScreen(navController, productoViewModel)
        }


    }
}
