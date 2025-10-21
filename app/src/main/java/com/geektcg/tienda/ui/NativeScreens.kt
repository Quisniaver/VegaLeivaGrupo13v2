package com.geektcg.tienda.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun GalleryPickerDemo() {
    var uri by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { res ->
        uri = res?.toString()
    }
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Galería (recurso nativo)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { launcher.launch("image/*") }) { Text("Elegir imagen") }
        Spacer(Modifier.height(8.dp))
        uri?.let {
            Image(painter = rememberAsyncImagePainter(it), contentDescription = null, modifier = Modifier.fillMaxWidth().height(180.dp), contentScale = ContentScale.Crop)
        }
    }
}

@Composable
fun LocationDemo() {
    val context = LocalContext.current
    val fusedClient: FusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val requestPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perms ->
        val ok = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true || perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (ok) {
            getLastLocation(fusedClient, onSuccess = { location = it }, onError = { error = it })
        } else {
            error = "Permiso de ubicación denegado"
        }
    }

    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Ubicación (recurso nativo)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { requestPermission.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) }) {
            Text("Obtener ubicación")
        }
        Spacer(Modifier.height(8.dp))
        if (location != null) Text("Lat: ${'$'}{location!!.latitude}, Lng: ${'$'}{location!!.longitude}")
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}

@SuppressLint("MissingPermission")
private fun getLastLocation(
    fused: FusedLocationProviderClient,
    onSuccess: (Location?) -> Unit,
    onError: (String) -> Unit
) {
    fused.lastLocation
        .addOnSuccessListener { onSuccess(it) }
        .addOnFailureListener { onError(it.message ?: "Error ubicacion") }
}