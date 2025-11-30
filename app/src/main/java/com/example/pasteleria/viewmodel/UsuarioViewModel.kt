package com.example.pasteleria.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pasteleria.model.Usuario
import com.example.pasteleria.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioRepository = UsuarioRepository(application.applicationContext)

    private val _usuarioLogueado = MutableStateFlow<Usuario?>(null)
    val usuarioLogueado: StateFlow<Usuario?> = _usuarioLogueado

    fun registrar(usuario: Usuario): Boolean {
        return usuarioRepository.registrarUsuario(usuario)
    }
    fun login(email: String, pass: String): Boolean {
        val usuarioEncontrado = usuarioRepository.loginUsuario(email, pass)
        if (usuarioEncontrado != null) {
            _usuarioLogueado.value = usuarioEncontrado
            return true
        }
        return false
    }

    fun logout() {
        _usuarioLogueado.value = null
    }
    fun cambiarFotoPerfil(uri: String) {
        val usuario = _usuarioLogueado.value
        if (usuario != null) {
            usuarioRepository.actualizarImagenUsuario(usuario.email, uri)
            _usuarioLogueado.value = usuario.copy(imagen = uri)
        }
    }
}