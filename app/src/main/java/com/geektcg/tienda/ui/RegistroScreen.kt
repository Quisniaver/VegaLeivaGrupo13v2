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
import com.geektcg.tienda.ui.vm.RegistroViewModel

@Composable
fun RegistroScreen(vm: RegistroViewModel = viewModel()) {
    val st = vm.state
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Registro", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = st.nombre, onValueChange = { vm.onNombre(it) },
            label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(),
            isError = st.nombreError != null, supportingText = { st.nombreError?.let { Text(it) } }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = st.email, onValueChange = { vm.onEmail(it) },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            isError = st.emailError != null, supportingText = { st.emailError?.let { Text(it) } }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = st.pass1, onValueChange = { vm.onPass1(it) },
            label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = st.pass2, onValueChange = { vm.onPass2(it) },
            label = { Text("Repetir Contraseña") }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = st.passError != null, trailingIcon = { if (st.passError != null) Icon(Icons.Default.Error, null) },
            supportingText = { st.passError?.let { Text(it) } }
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { if (vm.validate()) {/* registrar */} }, modifier = Modifier.fillMaxWidth()) { Text("Crear cuenta") }
    }
}