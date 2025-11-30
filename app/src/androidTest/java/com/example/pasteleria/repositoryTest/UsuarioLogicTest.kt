package com.example.pasteleria.repositoryTest

import androidx.test.platform.app.InstrumentationRegistry
import com.example.pasteleria.model.Usuario
import com.example.pasteleria.repository.UsuarioRepository
import org.junit.Assert.*
import org.junit.Test

class UsuarioLogicTest {

    @Test
    fun registroDeUsuarioFuncionaCorrectamente() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val repositorio = UsuarioRepository(appContext)
        
        val randomId = (1..99999).random()
        val usuarioNuevo = Usuario(
            id = randomId,
            nombre = "Usuario Test",
            email = "test_registro_$randomId@mail.com",
            password = "123",
            imagen = null
        )

        val resultado = repositorio.registrarUsuario(usuarioNuevo)

        assertTrue("El registro debería ser exitoso", resultado)
    }

    @Test
    fun loginDeUsuarioFuncionaCorrectamente() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val repositorio = UsuarioRepository(appContext)
        val randomId = (1..99999).random()
        val email = "test_login_$randomId@mail.com"
        val password = "passwordSegura"

        val usuarioOriginal = Usuario(randomId, "Juan Perez", email, password, null)
        repositorio.registrarUsuario(usuarioOriginal)

        val usuarioEncontrado = repositorio.loginUsuario(email, password)

        assertNotNull("Debería encontrar al usuario", usuarioEncontrado)
        assertEquals("Juan Perez", usuarioEncontrado?.nombre)

        val usuarioNoEncontrado = repositorio.loginUsuario(email, "claveErronea")

        assertNull("No debería dejar entrar con clave mala", usuarioNoEncontrado)
    }
}