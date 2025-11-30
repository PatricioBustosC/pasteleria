package com.example.pasteleria

import java.security.MessageDigest

object SeguridadUtils {
    fun encriptar(texto: String): String {
        val bytes = texto.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}