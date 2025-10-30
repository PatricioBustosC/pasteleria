package com.example.pasteleria.repository

import com.example.pasteleria.model.Usuario

class UsuarioRepository {

    private val usuarios = mutableListOf<Usuario>()

    fun registrarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun login(email: String, password: String): Usuario? {
        return usuarios.find { it.email == email && it.password == password }
    }
}
