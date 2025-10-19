package com.geektcg.tienda.ui.theme

import android.app.Activity
import androidx.compose.ui.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.geektcg.tienda.ui.theme.Typography
// ❗ ASUME: Debes tener RoyalBlue, RoyalGrey, RoyalBackground y SkyBlue40 definidas en Color.kt
// Si no están en Color.kt, debes importarlas o definirlas aquí.

// 🎨 Esquema de colores claro
private val LightColorScheme = lightColorScheme(
    primary = RoyalBlue,          // 💙 Azul príncipe
    secondary = RoyalGrey,
    tertiary = SkyBlue40,
    background = RoyalBackground,
    surface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF3756B0),  // Azul más oscuro
    secondary = Color(0xFFB0B6C8),
    tertiary = Color(0xFF90CAF9),
    background = Color(0xFF0D1117),
    surface = Color(0xFF1C1F26)
)


/**
 * 🌟 Tema principal de la app Geek TCG / TiendaTheme
 * Compatible con dynamic color (Android 12+) y modo oscuro
 */
@Composable
fun TiendaTheme( // ❗ CORRECCIÓN 1: Renombrado a TiendaTheme (asumido)
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // ❗ CORRECCIÓN 2: Cambiado de AppTypography a Typography
        shapes = Shapes,        // ❗ CORRECCIÓN 3: Cambiado de AppShapes a Shapes (asumido)
        content = content
    )
}