package com.geektcg.tienda.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geektcg.tienda.ui.vm.ContactoViewModel

@Composable
fun ContactoScreen(vm: ContactoViewModel = viewModel()) {
    val st = vm.state
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Contacto", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = st.nombre, onValueChange = { vm.onNombre(it) }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(), isError = st.nombreError != null, supportingText = { st.nombreError?.let { Text(it) } })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = st.email, onValueChange = { vm.onEmail(it) }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), isError = st.emailError != null, supportingText = { st.emailError?.let { Text(it) } })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = st.mensaje, onValueChange = { vm.onMensaje(it) }, label = { Text("Mensaje") }, modifier = Modifier.fillMaxWidth(), minLines = 4, isError = st.mensajeError != null, supportingText = { st.mensajeError?.let { Text(it) } })
        Spacer(Modifier.height(12.dp))
        Button(onClick = { vm.enviar() }) { Text("Enviar") }
        AnimatedVisibility(visible = st.enviado) {
            Text("¡Mensaje enviado!", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 12.dp))
        }
    }
}