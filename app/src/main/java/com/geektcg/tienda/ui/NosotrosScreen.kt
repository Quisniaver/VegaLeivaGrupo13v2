package com.geektcg.tienda.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task

// --- Pide permisos al iniciar la pantalla ---
@Composable
fun PedirPermisos() {
    val permisosLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {}
    )
    LaunchedEffect(Unit) {
        permisosLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        )
    }
}

@Composable
fun NosotrosScreen() {
    PedirPermisos()

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Estado para ubicación
    var latitud by remember { mutableStateOf<Double?>(null) }
    var longitud by remember { mutableStateOf<Double?>(null) }
    var mensaje by remember { mutableStateOf("") }

    // Estado para las imágenes seleccionadas
    var imagenes by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // Launcher para seleccionar múltiples imágenes
    val galeriaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            if (uris.isNotEmpty()) {
                imagenes = uris
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Título ---
        Text(
            text = "Nosotros",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(16.dp))

        // --- Descripción ---
        Text(
            text = """
En Geek TCG somos más que una tienda: somos una comunidad apasionada por el universo del **Trading Card Game.

Ofrecemos productos de las sagas más icónicas como Pokémon, Yu-Gi-Oh! y Mitos y Leyendas, además de accesorios exclusivos para coleccionistas y jugadores.

            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 17.sp,
                textAlign = TextAlign.Justify
            )
        )

        Spacer(Modifier.height(24.dp))

        // --- Botón de galería ---
        Text(
            text = "Galería de la tienda",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = { galeriaLauncher.launch("image/*") }) {
            Text("Seleccionar fotos desde la galería")
        }

        Spacer(Modifier.height(16.dp))

        if (imagenes.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(imagenes) { uri ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.size(width = 200.dp, height = 130.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Imagen seleccionada",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        // --- Ubicación ---
        Text(
            text = "Ubicación actual",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            obtenerUbicacion(
                context,
                fusedLocationClient,
                onSuccess = { location ->
                    latitud = location.latitude
                    longitud = location.longitude
                    mensaje = "Ubicación obtenida con éxito ✅"
                },
                onError = { error ->
                    mensaje = error
                }
            )
        }) {
            Text("Obtener ubicación")
        }

        Spacer(Modifier.height(16.dp))

        latitud?.let { Text("Latitud: $it") }
        longitud?.let { Text("Longitud: $it") }
        Text(mensaje, color = Color.Gray)

        Spacer(Modifier.height(32.dp))

        Text(
            text = "¡Gracias por ser parte del universo Geek TCG!",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@SuppressLint("MissingPermission")
fun obtenerUbicacion(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onSuccess: (Location) -> Unit,
    onError: (String) -> Unit
) {
    val permisoFine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    val permisoCoarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

    if (permisoFine == PackageManager.PERMISSION_GRANTED || permisoCoarse == PackageManager.PERMISSION_GRANTED) {
        val locationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        )

        locationTask.addOnSuccessListener { location ->
            if (location != null) onSuccess(location)
            else onError("No se pudo obtener la ubicación, intenta nuevamente.")
        }.addOnFailureListener {
            onError("Error al obtener ubicación: ${it.message}")
        }
    } else {
        onError("Permisos de ubicación no otorgados ⚠️")
    }
}
