package com.geektcg.tienda.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.geektcg.tienda.vm.SessionManager
import com.geektcg.tienda.vm.UsuarioStorage

@Composable
fun UsuariosScreen() {
    val context = LocalContext.current
    val currentUser = SessionManager.currentUser

    // Si no hay sesión
    if (currentUser == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Debes iniciar sesión para acceder 🔒")
        }
        return
    }

    // Si no es admin
    if (!currentUser.isAdmin) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Acceso denegado ❌ Solo el administrador puede ver esta sección.")
        }
        return
    }

    // Si es admin
    val usuarios = remember { mutableStateListOf(*UsuarioStorage.obtenerUsuarios(context).toTypedArray()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Panel de Administración 👑", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Text("Usuarios registrados: ${usuarios.size}", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))

        usuarios.forEach { usuario ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(usuario.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(usuario.email, style = MaterialTheme.typography.bodySmall)
                    }

                    // Evita eliminar al admin
                    if (!usuario.isAdmin) {
                        TextButton(
                            onClick = {
                                UsuarioStorage.eliminarUsuario(context, usuario.email)
                                usuarios.remove(usuario)
                                Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text("Eliminar", color = Color.Red)
                        }
                    }
                }
            }
        }

        if (usuarios.none { !it.isAdmin }) {
            Spacer(Modifier.height(12.dp))
            Text("No hay usuarios registrados aún 👥", color = Color.Gray)
        }
    }
}
