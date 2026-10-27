package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldGreen,
    onPrimary = Color.White,
    primaryContainer = DeepForestGreen,
    onPrimaryContainer = SoftGold,
    secondary = RoyalGold,
    onSecondary = DarkGreen,
    secondaryContainer = DarkModeSurfaceElevated,
    onSecondaryContainer = SoftGold,
    tertiary = SoftGold,
    onTertiary = DarkGreen,
    background = DarkModeBackground,
    onBackground = DarkModeText,
    surface = DarkModeSurface,
    onSurface = DarkModeText,
    surfaceVariant = DarkModeSurfaceElevated,
    onSurfaceVariant = DarkModeSecondaryText,
    outline = DarkModeBorder,
    outlineVariant = DarkModeBorder
)

private val LightColorScheme = lightColorScheme(
    primary = DeepForestGreen,
    onPrimary = Color.White,
    primaryContainer = SoftGreenSurface,
    onPrimaryContainer = PrimaryTextLight,
    secondary = EmeraldGreen,
    onSecondary = Color.White,
    secondaryContainer = SoftGreenSurface,
    onSecondaryContainer = PrimaryTextLight,
    tertiary = RoyalGold,
    onTertiary = PrimaryTextLight,
    background = WarmWhite,
    onBackground = PrimaryTextLight,
    surface = CardBackgroundLight,
    onSurface = PrimaryTextLight,
    surfaceVariant = SoftGreenSurface,
    onSurfaceVariant = SecondaryTextLight,
    outline = BorderLight,
    outlineVariant = BorderLight
)

@Composable
fun YannQuranTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
