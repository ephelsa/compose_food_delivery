package com.github.ephelsa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = JadeGreen,
    primaryVariant = ShamrockGreen,
    secondary = MacaroniAndCheese,
    secondaryVariant = MacaroniAndCheese,
    background = Onyx,
    surface = Onyx,
    error = Cardinal,
    onPrimary = Onyx,
    onSecondary = Onyx,
    onBackground = White,
    onSurface = White,
    onError = Onyx
)

private val LightColorPalette = lightColors(
    primary = JadeGreen,
    primaryVariant = ShamrockGreen,
    secondary = MacaroniAndCheese,
    secondaryVariant = MacaroniAndCheese,
    background = White,
    surface = White,
    error = Cardinal,
    onPrimary = White,
    onSecondary = Night,
    onBackground = Night,
    onSurface = Night,
    onError = White
)

@Composable
fun ComposeFoodDeliveryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}