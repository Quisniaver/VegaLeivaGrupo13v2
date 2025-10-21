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
import com.geektcg.tienda.vm.RegistroViewModel

@Composable
fun RegistroScreen(vm: RegistroViewModel = viewModel()) {
    val st = vm.state
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registro",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        // 🔹 Campo Nombre
        OutlinedTextField(
            value = st.nombre,
            onValueChange = { vm.onNombre(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = st.nombreError != null,
            supportingText = { st.nombreError?.let { Text(it) } }
        )

        Spacer(Modifier.height(8.dp))

        // 🔹 Campo Email
        OutlinedTextField(
            value = st.email,
            onValueChange = { vm.onEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = st.emailError != null,
            supportingText = { st.emailError?.let { Text(it) } }
        )

        Spacer(Modifier.height(8.dp))

        // 🔹 Contraseña (campo 1)
        OutlinedTextField(
            value = st.pass1,
            onValueChange = { vm.onPass1(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = st.pass1Error != null,
            trailingIcon = {
                if (st.pass1Error != null) Icon(Icons.Default.Error, contentDescription = null)
            },
            supportingText = { st.pass1Error?.let { Text(it) } }
        )

        Spacer(Modifier.height(8.dp))

        // 🔹 Repetir Contraseña (campo 2)
        OutlinedTextField(
            value = st.pass2,
            onValueChange = { vm.onPass2(it) },
            label = { Text("Repetir Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = st.pass2Error != null,
            trailingIcon = {
                if (st.pass2Error != null) Icon(Icons.Default.Error, contentDescription = null)
            },
            supportingText = { st.pass2Error?.let { Text(it) } }
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 Botón Crear cuenta
        Button(
            onClick = {
                if (vm.crearCuentaLocal()) {
                    Toast.makeText(ctx, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }
    }
}
