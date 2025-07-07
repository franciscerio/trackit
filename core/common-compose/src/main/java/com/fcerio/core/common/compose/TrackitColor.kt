package com.fcerio.core.common.compose

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

//private val DarkColorScheme = darkColorScheme(
//    primary = Color(0xFF004AD9),
//    onPrimary = Color(0xFF4D80EA),
//    primaryContainer = Color(0xFFE5EDFB),
//    onPrimaryContainer = Color(0xFFF2F6FD),
//    inversePrimary = Color(0xFF050529),
//    secondary = Color(0xFFB4B4BF),
//    tertiary = Color(0xFF828294),
//    surfaceContainer = Color(0xFFB4B4BF),
//    onSecondary = Color(0xFFFFFFFF),
//    surfaceBright = Color(0xFF7DDB25),
//    surfaceTint = Color(0xFF00B2F4),
//    surfaceDim = Color(0xFFFFCE22),
//    error = Color(0xFFF10021),
//    background = Color(0xFFFFFFFF),
//    surface = Color(0xFFF7F7F9),
//    surfaceVariant = Color(0xFFF2F2F4),
//    outlineVariant = Color(0xFFE6E6EA),
//    outline = Color(0xFFE5E5E5)
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Color(0xFF004AD9),
//    onPrimary = Color(0xFF4D80EA),
//    primaryContainer = Color(0xFFE5EDFB),
//    onPrimaryContainer = Color(0xFFF2F6FD),
//    inversePrimary = Color(0xFF050529),
//    secondary = Color(0xFFB4B4BF),
//    tertiary = Color(0xFF828294),
//    surfaceContainer = Color(0xFFB4B4BF),
//    onSecondary = Color(0xFFFFFFFF),
//    surfaceBright = Color(0xFF7DDB25),
//    surfaceTint = Color(0xFF00B2F4),
//    surfaceDim = Color(0xFFFFCE22),
//    error = Color(0xFFF10021),
//    background = Color(0xFFFFFFFF),
//    surface = Color(0xFFF7F7F9),
//    surfaceVariant = Color(0xFFF2F2F4),
//    outlineVariant = Color(0xFFE6E6EA),
//    outline = Color(0xFFE5E5E5)
//)

@Immutable
data class TrackitColor(
    val primaryDefault: Color = Color.Unspecified,
    val primaryShade1: Color = Color.Unspecified,
    val primaryShade2: Color = Color.Unspecified,
    val primaryShade3: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textTertiary: Color = Color.Unspecified,
    val textDisabled: Color = Color.Unspecified,
    val textWhite: Color = Color.Unspecified,
    val semanticsSuccess: Color = Color.Unspecified,
    val semanticsInfo: Color = Color.Unspecified,
    val semanticsWarning: Color = Color.Unspecified,
    val semanticsError: Color = Color.Unspecified,
    val neutralsBackground: Color = Color.Unspecified,
    val neutralsCards: Color = Color.Unspecified,
    val neutralsFieldsAndTags: Color = Color.Unspecified,
    val neutralsStroke: Color = Color.Unspecified,
    val neutralsBorderAndDivider: Color = Color.Unspecified
)

val DarkColor = TrackitColor(
    primaryDefault = Color(0xFF000000),
    primaryShade1 = Color(0xFF4D80EA),
    primaryShade2 = Color(0xFFE5EDFB),
    primaryShade3 = Color(0xFFF2F6FD),
    textPrimary = Color(0xFFE91E63),
    textSecondary = Color(0xFFB4B4BF),
    textTertiary = Color(0xFF828294),
    textDisabled = Color(0xFFB4B4BF),
    textWhite = Color(0xFFFFFFFF),
    semanticsSuccess = Color(0xFF7DDB25),
    semanticsInfo = Color(0xFF00B2F4),
    semanticsWarning = Color(0xFFFFCE22),
    semanticsError = Color(0xFFF10021),
    neutralsBackground = Color(0xFFFFFFFF),
    neutralsCards = Color(0xFFF7F7F9),
    neutralsFieldsAndTags = Color(0xFFF2F2F4),
    neutralsStroke = Color(0xFFE6E6EA),
    neutralsBorderAndDivider = Color(0xFFE5E5E5)
)

val LightColor = TrackitColor(
    primaryDefault = Color(0xFF004AD9),
    primaryShade1 = Color(0xFF4D80EA),
    primaryShade2 = Color(0xFFE5EDFB),
    primaryShade3 = Color(0xFFF2F6FD),
    textPrimary = Color(0xFF050529),
    textSecondary = Color(0xFFB4B4BF),
    textTertiary = Color(0xFF828294),
    textDisabled = Color(0xFFB4B4BF),
    textWhite = Color(0xFFFFFFFF),
    semanticsSuccess = Color(0xFF7DDB25),
    semanticsInfo = Color(0xFF00B2F4),
    semanticsWarning = Color(0xFFFFCE22),
    semanticsError = Color(0xFFF10021),
    neutralsBackground = Color(0xFFFFFFFF),
    neutralsCards = Color(0xFFF7F7F9),
    neutralsFieldsAndTags = Color(0xFFF2F2F4),
    neutralsStroke = Color(0xFFE6E6EA),
    neutralsBorderAndDivider = Color(0xFFE5E5E5)
)

val LocalTrackitColors = staticCompositionLocalOf {
    TrackitColor()
}
