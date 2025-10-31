package com.example.pasteleria.repository

import com.example.pasteleria.model.Usuario

class UsuarioRepository {

    private val listaUsuarios = mutableListOf<Usuario>()

    fun registrarUsuario(usuario: Usuario): Boolean {
        if (listaUsuarios.any { it.email.equals(usuario.email, ignoreCase = true) }) {
            return false
        }
        listaUsuarios.add(usuario)
        return true
    }

    fun loginUsuario(email: String, password: String): Usuario? {
        return listaUsuarios.find { it.email.equals(email, ignoreCase = true) && it.password == password }
    }

    fun obtenerUsuarios(): List<Usuario> {
        return listaUsuarios
    }
}
