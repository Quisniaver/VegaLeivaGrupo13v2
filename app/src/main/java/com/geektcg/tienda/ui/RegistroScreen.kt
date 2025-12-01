package com.geektcg.tienda.ui
import androidx.compose.ui.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.geektcg.tienda.vm.RegistroViewModel

@Composable
fun RegistroScreen(
    navController: NavController,
    vm: RegistroViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.nombre,
            onValueChange = vm::onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.errorNombre != null,
            supportingText = { state.errorNombre?.let { Text(it) } },
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = vm::onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.errorEmail != null,
            supportingText = { state.errorEmail?.let { Text(it) } }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.pass1,
            onValueChange = vm::onPass1Change,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = state.errorPass1 != null,
            supportingText = { state.errorPass1?.let { Text(it) } }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.pass2,
            onValueChange = vm::onPass2Change,
            label = { Text("Repetir contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = state.errorPass2 != null,
            supportingText = { state.errorPass2?.let { Text(it) } }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                vm.registrarUsuario(
                    onSuccess = {
                        Toast.makeText(
                            context,
                            "Cuenta creada con éxito",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0D47A1),   // azul antiguo
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = { navController.popBackStack() }
        ) {
            Text("¿Ya tienes cuenta? Iniciar sesión")
        }}}
