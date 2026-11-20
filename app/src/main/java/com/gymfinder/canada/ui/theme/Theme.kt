package com.gymfinder.canada.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GymFinderDarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,
    primaryContainer = DarkOrangeAccent,
    onPrimaryContainer = SecondaryOrange,
    secondary = SecondaryOrange,
    onSecondary = Color.Black,
    background = MainBackground,
    onBackground = PrimaryText,
    surface = SecondaryBackground,
    onSurface = PrimaryText,
    surfaceVariant = CardBackground,
    onSurfaceVariant = SecondaryText,
    outline = CardBorder
)

@Composable
fun GymFinderTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GymFinderDarkColorScheme,
        typography = Typography,
        content = content
    )
}
