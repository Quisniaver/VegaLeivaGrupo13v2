package com.geektcg.tienda.vm

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.geektcg.tienda.data.UserPreferences
import kotlinx.coroutines.launch

// 🔹 Estado del formulario
data class RegistroState(
    val nombre: String = "",
    val email: String = "",
    val pass1: String = "",
    val pass2: String = "",
    val nombreError: String? = null,
    val emailError: String? = null,
    val pass1Error: String? = null,
    val pass2Error: String? = null
)

// 🔹 ViewModel principal
class RegistroViewModel(app: Application) : AndroidViewModel(app) {

    var state by mutableStateOf(RegistroState())
        private set

    private val prefs = UserPreferences(app) // DataStore

    // 🔹 Actualizadores de campos
    fun onNombre(v: String) {
        state = state.copy(nombre = v, nombreError = null)
    }

    fun onEmail(v: String) {
        state = state.copy(email = v, emailError = null)
    }

    fun onPass1(v: String) {
        state = state.copy(pass1 = v, pass1Error = null)
    }

    fun onPass2(v: String) {
        state = state.copy(pass2 = v, pass2Error = null)
    }

    private fun isEmailValid(e: String) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()

    // 🔹 Validación completa
    fun validate(): Boolean {
        var valid = true
        var nErr: String? = null
        var eErr: String? = null
        var p1Err: String? = null
        var p2Err: String? = null

        // 🟣 Validar nombre
        when {
            state.nombre.isBlank() -> {
                nErr = "El nombre no puede estar vacío"
                valid = false
            }
            state.nombre.length > 10 -> {
                nErr = "Máximo 10 caracteres"
                valid = false
            }
            !state.nombre.matches(Regex("^[A-Za-z0-9]+$")) -> {
                nErr = "Solo letras y números, sin símbolos"
                valid = false
            }
        }

        // 🟣 Validar email
        if (!isEmailValid(state.email)) {
            eErr = "Email inválido"
            valid = false
        }

        // 🟣 Validar contraseña principal
        when {
            state.pass1.isBlank() -> {
                p1Err = "La contraseña no puede estar vacía"
                valid = false
            }
            state.pass1.length < 6 -> {
                p1Err = "Debe tener al menos 6 caracteres"
                valid = false
            }
            !state.pass1.matches(Regex(".*\\d.*")) -> {
                p1Err = "Debe contener al menos un número"
                valid = false
            }
        }

        // 🟣 Validar coincidencia
        if (state.pass1 != state.pass2 && state.pass2.isNotEmpty()) {
            p2Err = "Las contraseñas no coinciden"
            valid = false
        }

        // 🟣 Actualizar estado
        state = state.copy(
            nombreError = nErr,
            emailError = eErr,
            pass1Error = p1Err,
            pass2Error = p2Err
        )

        return valid
    }

    // 🔹 Guardar cuenta en almacenamiento local
    fun crearCuentaLocal(): Boolean {
        val valid = validate()
        if (!valid) return false

        viewModelScope.launch {
            prefs.saveUser(
                name = state.nombre,
                email = state.email,
                pass = state.pass1
            )
        }
        return true
    }
}
