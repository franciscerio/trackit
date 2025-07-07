package com.fcerio.core.common.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp

@Composable
fun TrackitTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colors: TrackitColor = if (isDarkTheme) DarkColor else LightColor,
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
        titleMedium = typography.title1,
        titleSmall = typography.subHeadline,
        labelLarge = typography.caption,
        labelMedium = typography.headline
    )

    MaterialTheme(
        typography = materialTypography,
        shapes = shapes
    ) {
        CompositionLocalProvider(
            LocalTrackitColors provides colors,
            LocalTypography provides typography,
            content = content
        )
    }
}

object AppTheme {

    val colors: TrackitColor
        @Composable
        @ReadOnlyComposable
        get() = LocalTrackitColors.current

    val typography: TrackitTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}