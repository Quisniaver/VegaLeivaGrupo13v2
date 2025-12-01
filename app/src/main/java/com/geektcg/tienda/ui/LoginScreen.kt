package com.geektcg.tienda.ui

import androidx.compose.ui.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.geektcg.tienda.vm.LoginViewModel
import com.geektcg.tienda.vm.SessionManager
import com.geektcg.tienda.vm.Usuario
import com.geektcg.tienda.vm.UsuarioStorage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    onRegistro: () -> Unit,
    vm: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = vm.state
    var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    val scope = rememberCoroutineScope()

    // Cargar usuarios del backend al iniciar
    LaunchedEffect(Unit) {
        usuarios = UsuarioStorage.obtenerUsuariosRemotos()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text("Ingresar", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            value = state.user,
            onValueChange = { vm.onUserChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.userError != null,
            supportingText = {
                state.userError?.let { Text(it) }
            }


        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.pass,
            onValueChange = { vm.onPassChange(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = state.passError != null,
            supportingText = {
                state.passError?.let { Text(it) }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!vm.validate()) return@Button

                scope.launch {
                    val user = usuarios.firstOrNull {
                        it.email == state.user && it.password == state.pass
                    }

                    if (user != null) {
                        SessionManager.currentUser = user
                        Toast.makeText(context, "Bienvenido ${user.nombre}", Toast.LENGTH_SHORT).show()
                        navController.navigate("inicio")
                    } else {
                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0D47A1),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp) // opcional visual antiguo
        ) {
            Text("Entrar")
        }

        TextButton(onClick = onRegistro) {
            Text("Crear cuenta")
        }
    }
}
