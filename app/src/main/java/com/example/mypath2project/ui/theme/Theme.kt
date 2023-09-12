package com.example.mypath2project.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4285F4), // Google Blue
    secondary = Color(0xFF0F9D58), // Google Green
    tertiary = Color(0xFFDB4437), // Google Red
    background = Color(0xFF1C1B1F), // Dark background
    surface = Color(0xFF1C1B1F), // Dark surface
    onPrimary = Color(0xFFFFFFFF), // White text on primary
    onSecondary = Color(0xFFFFFFFF), // White text on secondary
    onTertiary = Color(0xFFFFFFFF), // White text on tertiary
    onBackground = Color(0xFFFFFFFF), // White text on background
    onSurface = Color(0xFFFFFFFF) // White text on surface
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4285F4), // Google Blue
    secondary = Color(0xFF0F9D58), // Google Green
    tertiary = Color(0xFFDB4437), // Google Red
    background = Color(0xFFFFFFFF), // White background
    surface = Color(0xFFFFFFFF), // White surface
    onPrimary = Color(0xFFFFFFFF), // White text on primary
    onSecondary = Color(0xFFFFFFFF), // White text on secondary
    onTertiary = Color(0xFFFFFFFF), // White text on tertiary
    onBackground = Color(0xFF1C1B1F), // Dark text on background
    onSurface = Color(0xFF1C1B1F) // Dark text on surface
)

@Composable
fun MyPath2ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}