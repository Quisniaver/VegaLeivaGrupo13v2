package com.geektcg.tienda.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginState(
    val user: String = "",
    val pass: String = "",
    val userError: String? = null,
    val passError: String? = null
)

class LoginViewModel : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onUserChange(v: String) { state = state.copy(user = v, userError = null) }
    fun onPassChange(v: String) { state = state.copy(pass = v, passError = null) }

    fun validate(): Boolean {
        var ok = true
        var userErr: String? = null
        var passErr: String? = null
        if (state.user.isBlank()) { userErr = "Usuario requerido"; ok = false }
        if (state.pass.length < 4) { passErr = "Mínimo 4 caracteres"; ok = false }
        state = state.copy(userError = userErr, passError = passErr)
        return ok
    }
}