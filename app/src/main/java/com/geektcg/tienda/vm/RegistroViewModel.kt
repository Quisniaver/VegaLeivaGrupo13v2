package com.geektcg.tienda.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class RegistroState(
    val nombre: String = "",
    val email: String = "",
    val pass1: String = "",
    val pass2: String = "",
    val nombreError: String? = null,
    val emailError: String? = null,
    val passError: String? = null
)

class RegistroViewModel : ViewModel() {
    var state by mutableStateOf(RegistroState())
        private set

    fun onNombre(v: String) { state = state.copy(nombre = v, nombreError = null) }
    fun onEmail(v: String) { state = state.copy(email = v, emailError = null) }
    fun onPass1(v: String) { state = state.copy(pass1 = v, passError = null) }
    fun onPass2(v: String) { state = state.copy(pass2 = v, passError = null) }

    private fun isEmailValid(e: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()

    fun validate(): Boolean {
        var ok = true
        var nErr: String? = null
        var eErr: String? = null
        var pErr: String? = null
        if (state.nombre.isBlank()) { nErr = "Nombre requerido"; ok = false }
        if (!isEmailValid(state.email)) { eErr = "Email inválido"; ok = false }
        if (state.pass1.length < 6 || state.pass1 != state.pass2) { pErr = "Contraseñas no coinciden o muy cortas"; ok = false }
        state = state.copy(nombreError = nErr, emailError = eErr, passError = pErr)
        return ok
    }
}