package com.example.pasteleria.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.pasteleria.model.Usuario
import com.example.pasteleria.SeguridadUtils // Importamos la herramienta de seguridad

class UsuarioRepository(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("pasteleria_db", Context.MODE_PRIVATE)
    private val gson = Gson()
    private var listaUsuarios = mutableListOf<Usuario>()

    init {
        cargarDatos()
    }

    fun registrarUsuario(usuario: Usuario): Boolean {
        if (listaUsuarios.any { it.email.equals(usuario.email, ignoreCase = true) }) {
            return false
        }

        val usuarioSeguro = usuario.copy(
            password = SeguridadUtils.encriptar(usuario.password)
        )

        listaUsuarios.add(usuarioSeguro)
        guardarDatos()
        return true
    }

    fun loginUsuario(email: String, password: String): Usuario? {
        val passwordEncriptada = SeguridadUtils.encriptar(password)

        return listaUsuarios.find {
            it.email.equals(email, ignoreCase = true) && it.password == passwordEncriptada
        }
    }

    fun obtenerUsuarios(): List<Usuario> {
        return listaUsuarios
    }

    private fun guardarDatos() {
        val json = gson.toJson(listaUsuarios)
        sharedPreferences.edit().putString("usuarios_key", json).apply()
    }

    private fun cargarDatos() {
        val json = sharedPreferences.getString("usuarios_key", null)
        if (json != null) {
            val type = object : TypeToken<MutableList<Usuario>>() {}.type
            listaUsuarios = gson.fromJson(json, type)
        } else {
            listaUsuarios = mutableListOf()
        }
    }

    fun actualizarImagenUsuario(email: String, nuevaImagenUri: String) {
        val indice = listaUsuarios.indexOfFirst { it.email == email }
        if (indice != -1) {
            val usuarioActualizado = listaUsuarios[indice].copy(imagen = nuevaImagenUri)
            listaUsuarios[indice] = usuarioActualizado
            guardarDatos()
        }
    }
}