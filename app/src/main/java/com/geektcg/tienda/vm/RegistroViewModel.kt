package com.geektcg.tienda.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektcg.tienda.api.ApiClient
import com.geektcg.tienda.api.UsuarioApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val api = ApiClient.retrofit.create(UsuarioApi::class.java)

    private val _state = MutableStateFlow(RegistroState())
    val state: StateFlow<RegistroState> = _state

    // ---------- VALIDACIONES -----------

    private fun validarNombre(nombre: String): String? {
        return when {
            nombre.isBlank() -> "El nombre no puede estar vacío"
            nombre.length < 3 -> "Debe tener al menos 3 caracteres"
            else -> null
        }
    }

    private fun validarEmail(email: String): String? {
        return when {
            email.isBlank() -> "El email no puede estar vacío"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                "Formato de email inválido"
            else -> null
        }
    }

    private fun validarPass(pass: String): String? {
        return when {
            pass.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
            else -> null
        }
    }

    private fun validarIguales(pass1: String, pass2: String): String? {
        return if (pass1 != pass2) "Las contraseñas no coinciden" else null
    }

    private fun validarFormulario() {
        val s = _state.value

        val valido =
            s.errorNombre == null &&
                    s.errorEmail == null &&
                    s.errorPass1 == null &&
                    s.errorPass2 == null &&
                    s.nombre.isNotBlank() &&
                    s.email.isNotBlank() &&
                    s.pass1.isNotBlank() &&
                    s.pass2.isNotBlank()

        _state.value = _state.value.copy(valido = valido)
    }

    // ---------- ON CHANGE -----------

    fun onNombreChange(value: String) {
        _state.value = _state.value.copy(
            nombre = value,
            errorNombre = validarNombre(value)
        )
        validarFormulario()
    }

    fun onEmailChange(value: String) {
        _state.value = _state.value.copy(
            email = value,
            errorEmail = validarEmail(value)
        )
        validarFormulario()
    }

    fun onPass1Change(value: String) {
        _state.value = _state.value.copy(
            pass1 = value,
            errorPass1 = validarPass(value),
            errorPass2 = validarIguales(value, _state.value.pass2)
        )
        validarFormulario()
    }

    fun onPass2Change(value: String) {
        _state.value = _state.value.copy(
            pass2 = value,
            errorPass2 = validarIguales(_state.value.pass1, value)
        )
        validarFormulario()
    }

    // ---------- REGISTRAR -----------

    fun registrarUsuario(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val s = _state.value
        if (!s.valido) {
            onError("Formato Incorrecto")
            return
        }

        viewModelScope.launch {

            _state.value = _state.value.copy(loading = true)

            try {
                api.crearUsuario(
                    com.geektcg.tienda.vm.Usuario(
                        nombre = s.nombre,
                        email = s.email,
                        password = s.pass1
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                onError("Error registrando usuario: ${e.message}")
            }

            _state.value = _state.value.copy(loading = false)
        }
    }
}
