package com.example.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.pasteleria.model.Usuario
import com.example.pasteleria.repository.UsuarioRepository

class UsuarioViewModel : ViewModel() {
    private val repositorio = UsuarioRepository()
    var usuarioActual = mutableStateOf<Usuario?>(null)
        private set

    fun registrar(nombre: String, email: String, password: String) {
        val usuario = Usuario(id = 1, nombre = nombre, email = email, password = password)
        repositorio.registrarUsuario(usuario)
        usuarioActual.value = usuario
    }

    fun login(email: String, password: String): Boolean {
        val usuario = repositorio.autenticar(email, password)
        usuarioActual.value = usuario
        return usuario != null
    }
}
