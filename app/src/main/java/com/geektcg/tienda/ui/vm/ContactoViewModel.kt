package com.geektcg.tienda.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ContactoState(
    val nombre: String = "",
    val email: String = "",
    val mensaje: String = "",
    val nombreError: String? = null,
    val emailError: String? = null,
    val mensajeError: String? = null,
    val enviado: Boolean = false
)

class ContactoViewModel : ViewModel() {
    var state by mutableStateOf(ContactoState())
        private set

    fun onNombre(v: String) { state = state.copy(nombre = v, nombreError = null) }
    fun onEmail(v: String) { state = state.copy(email = v, emailError = null) }
    fun onMensaje(v: String) { state = state.copy(mensaje = v, mensajeError = null) }

    private fun isEmailValid(e: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()

    fun enviar(): Boolean {
        var ok = true
        var nErr: String? = null
        var eErr: String? = null
        var mErr: String? = null
        if (state.nombre.isBlank()) { nErr = "Nombre requerido"; ok = false }
        if (!isEmailValid(state.email)) { eErr = "Email inválido"; ok = false }
        if (state.mensaje.length < 10) { mErr = "Mensaje muy corto"; ok = false }
        state = state.copy(nombreError = nErr, emailError = eErr, mensajeError = mErr, enviado = ok)
        return ok
    }
}