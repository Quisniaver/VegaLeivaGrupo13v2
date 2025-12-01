package com.geektcg.tienda.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 🌞 MODO CLARO
private val LightColors = lightColorScheme(
    primary = PurpleMedium,
    secondary = PurpleLight,
    tertiary = PurpleDark,

    background = BackgroundLight,
    surface = SurfaceLight,

    onPrimary = TextLight,
    onSecondary = TextDark,
    onTertiary = TextLight,

    onBackground = TextDark,
    onSurface = TextDark
)

// 🌙 MODO OSCURO
private val DarkColors = darkColorScheme(
    primary = PurpleDark,
    secondary = PurpleMedium,
    tertiary = PurpleLight,

    background = Color(0xFF181020),
    surface = Color(0xFF221732),

    onPrimary = TextLight,
    onSecondary = TextLight,
    onTertiary = Color.Black,

    onBackground = TextLight,
    onSurface = TextLight
)

@Composable
fun LeivaVegaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColors else LightColors

    // ⭐ Control total de status Bar (solo morado)
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
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}