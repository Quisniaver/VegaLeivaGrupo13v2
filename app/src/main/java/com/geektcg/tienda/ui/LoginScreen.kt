package com.geektcg.tienda.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.geektcg.tienda.vm.*

@Composable
fun LoginScreen(
    navController: NavController,
    onRegistro: () -> Unit,
    vm: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = vm.state
    val usuarios = remember { UsuarioStorage.obtenerUsuarios(context) }
    LaunchedEffect(Unit) {
        UsuarioStorage.obtenerUsuarios(context) // 🔹 fuerza creación del admin si no existe
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ingresar", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.user,
            onValueChange = { vm.onUserChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.userError != null,
            supportingText = { state.userError?.let { Text(it) } }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.pass,
            onValueChange = { vm.onPassChange(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.passError != null,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                if (state.passError != null) Icon(Icons.Default.Error, contentDescription = null)
            },
            supportingText = { state.passError?.let { Text(it) } }
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                if (vm.validate()) {

                    val listaUsuarios = UsuarioStorage.obtenerUsuarios(context)

                    val usuario = listaUsuarios.find {
                        it.email.trim().equals(state.user.trim(), ignoreCase = true) &&
                                it.password == state.pass
                    }

                    if (usuario != null) {

                        SessionManager.currentUser = usuario


                        val msg = if (usuario.isAdmin)
                            "Bienvenido administrador 👑"
                        else
                            "Bienvenido ${usuario.nombre} 🎮"
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()


                        if (usuario.isAdmin)
                            navController.navigate("usuarios")
                        else
                            navController.navigate("inicio")
                    } else {
                        Toast.makeText(context, "Credenciales incorrectas ❌", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        TextButton(onClick = onRegistro) { Text("Crear cuenta") }
    }
}
