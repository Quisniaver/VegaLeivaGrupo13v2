package com.geektcg.tienda.vm

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RegistroViewModelTest {

    @Test
    fun nombre_vacio_marca_error() = runTest {
        val vm = RegistroViewModel()

        vm.onNombreChange("")

        assertEquals("El nombre no puede estar vacío", vm.state.value.errorNombre)
    }

    @Test
    fun email_invalido_marca_error() = runTest {
        val vm = RegistroViewModel()

        vm.onEmailChange("correo-malo")

        assertEquals("Formato de email inválido", vm.state.value.errorEmail)
    }
}
