package com.example.pasteleria.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pasteleria.model.Usuario
import com.example.pasteleria.repository.UsuarioRepository

class UsuarioViewModel : ViewModel() {

    var usuarioActual = mutableStateOf<Usuario?>(null)
    private val repositorio = UsuarioRepository()

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var nombre = mutableStateOf("")

    var emailError = mutableStateOf<String?>(null)
    var passwordError = mutableStateOf<String?>(null)
    var nombreError = mutableStateOf<String?>(null)

    fun validarRegistro(nombre: String, email: String, password: String): Boolean {
        var valido = true

        if (nombre.isBlank()) {
            nombreError.value = "Ingrese su nombre"
            valido = false
        } else {
            nombreError.value = null
        }

        if (email.isBlank()) {
            emailError.value = "Ingrese su correo"
            valido = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.value = "Correo inválido. Debe tener formato nombre@ejemplo.com"
            valido = false
        } else {
            emailError.value = null
        }

        if (password.length < 6) {
            passwordError.value = "La contraseña debe tener al menos 6 caracteres"
            valido = false
        } else {
            passwordError.value = null
        }

        return valido
    }
    fun validarLogin(email: String, password: String): Boolean {
        var valido = true

        if (email.isBlank()) {
            emailError.value = "Ingrese su correo"
            valido = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.value = "Correo inválido (ejemplo@correo.com)"
            valido = false
        } else {
            emailError.value = null
        }

        if (password.length < 4) {
            passwordError.value = "La contraseña debe tener al menos 4 caracteres"
            valido = false
        } else {
            passwordError.value = null
        }

        return valido
    }


    fun registrar(nombre: String, email: String, password: String): Boolean {
        val valido = validarRegistro(nombre, email, password)
        if (!valido) return false

        val usuario = Usuario(id = (0..1000).random(), nombre = nombre, email = email, password = password)
        val registrado = repositorio.registrarUsuario(usuario)

        if (!registrado) {
            emailError.value = "El correo ya está registrado"
            return false
        }

        usuarioActual.value = usuario
        return true
    }

}
