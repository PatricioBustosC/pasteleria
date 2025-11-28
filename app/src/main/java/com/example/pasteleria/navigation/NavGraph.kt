package com.example.pasteleria.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pasteleria.screens.*
import com.example.pasteleria.viewmodel.ProductoViewModel
import com.example.pasteleria.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext as Application

    val usuarioViewModel = remember { UsuarioViewModel(context) }
    val productoViewModel = remember { ProductoViewModel() }

    NavHost(navController = navController, startDestination = "login") {

        composable(route = "login") {
            LoginScreen(navController, usuarioViewModel)
        }

        composable(route = "registro") {
            RegisterScreen(navController, usuarioViewModel)
        }

        composable(route = "catalogo") {
            CatalogoScreen(navController, productoViewModel, usuarioViewModel)
        }

        composable(route = "carrito") {
            CarritoScreen(navController, productoViewModel, usuarioViewModel)
        }

        composable(route = "resumen") {
            ResumenScreen(navController, productoViewModel)
        }

        composable(route = "perfil") {
            PerfilScreen(navController, usuarioViewModel)
        }
    }
}