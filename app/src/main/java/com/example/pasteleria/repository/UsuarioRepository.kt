package com.example.pasteleria.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.pasteleria.model.Usuario

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
        listaUsuarios.add(usuario)
        guardarDatos()
        return true
    }

    fun loginUsuario(email: String, password: String): Usuario? {
        return listaUsuarios.find { it.email.equals(email, ignoreCase = true) && it.password == password }
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
}