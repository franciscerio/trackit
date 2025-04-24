package com.fcerio.core.common.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF004AD9),
    onPrimary = Color(0xFF4D80EA),
    primaryContainer = Color(0xFFE5EDFB),
    onPrimaryContainer = Color(0xFFF2F6FD),
    inversePrimary = Color(0xFF050529),
    secondary = Color(0xFFB4B4BF),
    tertiary = Color(0xFF828294),
    surfaceContainer = Color(0xFFB4B4BF),
    onSecondary = Color(0xFFFFFFFF),
    surfaceBright = Color(0xFF7DDB25),
    surfaceTint = Color(0xFF00B2F4),
    surfaceDim = Color(0xFFFFCE22),
    error = Color(0xFFF10021),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF7F7F9),
    surfaceVariant = Color(0xFFF2F2F4),
    outlineVariant = Color(0xFFE6E6EA),
    outline = Color(0xFFE5E5E5)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF004AD9),
    onPrimary = Color(0xFF4D80EA),
    primaryContainer = Color(0xFFE5EDFB),
    onPrimaryContainer = Color(0xFFF2F6FD),
    inversePrimary = Color(0xFF050529),
    secondary = Color(0xFFB4B4BF),
    tertiary = Color(0xFF828294),
    surfaceContainer = Color(0xFFB4B4BF),
    onSecondary = Color(0xFFFFFFFF),
    surfaceBright = Color(0xFF7DDB25),
    surfaceTint = Color(0xFF00B2F4),
    surfaceDim = Color(0xFFFFCE22),
    error = Color(0xFFF10021),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF7F7F9),
    surfaceVariant = Color(0xFFF2F2F4),
    outlineVariant = Color(0xFFE6E6EA),
    outline = Color(0xFFE5E5E5)
)

@Composable
fun TrackitTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme().not(),
    colors: ColorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme,
    typography: TrackitTypography = AppTheme.typography,
    shapes: Shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(8.dp)
    ),
    content: @Composable () -> Unit
) {

    val materialTypography = Typography(
        displayLarge = typography.largeTitle,
        displayMedium = typography.title1,
        displaySmall = typography.title2,
        headlineLarge = typography.title3,
        bodyLarge = typography.body1,
        bodyMedium = typography.body2,
        titleMedium = typography.subHeadlineMedium,
        titleSmall = typography.subHeadline,
        labelLarge = typography.caption,
        labelMedium = typography.headline
    )

    MaterialTheme(
        colorScheme = colors,
        typography = materialTypography,
        shapes = shapes
    ) {
        CompositionLocalProvider(
            LocalTypography provides typography,
            content = content
        )
    }
}

object AppTheme {
    /**
     * Retrieves the current [TrackitTypography] at the call site's position in the hierarchy.
     */
    val typography: TrackitTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}