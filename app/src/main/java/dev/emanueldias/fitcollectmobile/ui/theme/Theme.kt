package dev.emanueldias.fitcollectmobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AndroidGreen,
    secondary = AndroidGreenLight,
    tertiary = AndroidGreenDark,
    background = AndroidBlack,
    surface = AndroidSurfaceDark,
    onPrimary = AndroidBlack,
    onSecondary = AndroidBlack,
    onBackground = AndroidWhite,
    onSurface = AndroidWhite
)

private val LightColorScheme = lightColorScheme(
    primary = AndroidGreenDark,
    secondary = AndroidGreen,
    tertiary = AndroidGreenLight,
    background = AndroidLightGray,
    surface = AndroidSurfaceLight,
    onPrimary = AndroidWhite,
    onSecondary = AndroidBlack,
    onBackground = AndroidBlack,
    onSurface = AndroidBlack
)

@Composable
fun FitCollectMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S -> {
            val context = androidx.compose.ui.platform.LocalContext.current
            if (darkTheme) androidx.compose.material3.dynamicDarkColorScheme(context) else androidx.compose.material3.dynamicLightColorScheme(context)
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