package com.geektcg.tienda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geektcg.tienda.ui.vm.LoginViewModel

@Composable
fun LoginScreen(onRegistro: () -> Unit, vm: LoginViewModel = viewModel()) {
    val state = vm.state
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ingresar", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = state.user, onValueChange = { vm.onUserChange(it) },
            label = { Text("Usuario") }, modifier = Modifier.fillMaxWidth(),
            isError = state.userError != null,
            supportingText = { if (state.userError != null) Text(state.userError!!)}
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = state.pass, onValueChange = { vm.onPassChange(it) },
            label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(),
            isError = state.passError != null,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = { if (state.passError != null) Icon(Icons.Default.Error, contentDescription = null) },
            supportingText = { if (state.passError != null) Text(state.passError!!) }
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { if (vm.validate()) {/* success */} }, modifier = Modifier.fillMaxWidth()) { Text("Entrar") }
        TextButton(onClick = onRegistro) { Text("Crear cuenta") }
    }
}