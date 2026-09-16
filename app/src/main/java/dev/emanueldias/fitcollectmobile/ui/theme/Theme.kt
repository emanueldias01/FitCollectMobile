package dev.emanueldias.fitcollectmobile.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AndroidGreen,
    secondary = AndroidGreenLight,
    tertiary = AndroidGreenDark,
    background = AndroidBlack,
    surface = AndroidDarkGray,
    surfaceContainer = Color(0xFF1A1A1A),
    surfaceContainerLow = Color(0xFF111111),
    surfaceContainerHighest = Color(0xFF2A2A2A),
    primaryContainer = Color(0xFF104A26),
    onPrimaryContainer = AndroidGreenLight,
    onPrimary = AndroidBlack,
    onSecondary = AndroidBlack,
    onBackground = AndroidWhite,
    onSurface = AndroidWhite,
    onSurfaceVariant = Color(0xFFCCCCCC)
)

private val LightColorScheme = lightColorScheme(
    primary = AndroidGreenDark,
    secondary = AndroidGreen,
    tertiary = AndroidGreenLight,
    background = Color(0xFFF4F6F8),
    surface = AndroidSurfaceLight,
    surfaceContainer = Color(0xFFEEEEEE),
    surfaceContainerLow = Color(0xFFF9F9F9),
    surfaceContainerHighest = Color(0xFFE2E2E2),
    primaryContainer = Color(0xFFE8F5E9),
    onPrimaryContainer = AndroidGreenDark,
    onPrimary = AndroidWhite,
    onSecondary = AndroidBlack,
    onBackground = AndroidBlack,
    onSurface = AndroidBlack,
    onSurfaceVariant = Color(0xFF555555)
)

@Composable
fun FitCollectMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
