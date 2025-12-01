package com.geektcg.tienda.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.vm.Usuario
import com.geektcg.tienda.vm.UsuarioStorage
import kotlinx.coroutines.launch

@Composable
fun UsuariosScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }

    // Cargar usuarios desde el backend al iniciar
    LaunchedEffect(Unit) {
        usuarios = UsuarioStorage.obtenerUsuariosRemotos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Usuarios Registrados",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(usuarios) { user ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {

                        Text("Nombre: ${user.nombre}")
                        Text("Email: ${user.email}")
                        Text("Admin: ${if (user.isAdmin) "Sí" else "No"}")

                        Spacer(modifier = Modifier.height(8.dp))

                        if (!user.isAdmin) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        val ok = UsuarioStorage.eliminarUsuario(user.email)

                                        if (ok) {
                                            usuarios = usuarios.filterNot { it.email == user.email }
                                            Toast
                                                .makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT)
                                                .show()
                                        } else {
                                            Toast
                                                .makeText(context, "Error eliminando usuario", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Eliminar")
                            }
                        } else {
                            Text("(El usuario admin no puede eliminarse)")
                        }
                    }
                }
            }
        }
    }
}
