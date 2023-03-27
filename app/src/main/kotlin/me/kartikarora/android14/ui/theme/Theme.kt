package me.kartikarora.android14.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun Android14Theme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {

    val colors = when (useDynamicColor) {
        true -> with(LocalContext.current) {
            if (useDarkTheme) dynamicDarkColorScheme(this) else dynamicLightColorScheme(this)
        }
        false -> if (useDarkTheme) Schemes.DarkColors else Schemes.LightColors
    }

    TransparentDecorEffect(useDarkTheme)

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@Composable
fun TransparentDecorEffect(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            window.isNavigationBarContrastEnforced = false

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)
            windowsInsetsController.isAppearanceLightStatusBars = !useDarkTheme
            windowsInsetsController.isAppearanceLightNavigationBars = !useDarkTheme
        }
    }
}