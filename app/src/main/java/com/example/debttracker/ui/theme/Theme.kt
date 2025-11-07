package com.example.debttracker.ui.theme

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
    primary = Color(0xff2ab7ca),
    secondary = Color(0xff7da2c8),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = Color(0xff121212),
    onBackground = Color(0xffeaeaea),
    surface = Color(0xFF1e1e1e),
    onSurface = Color(0xffeaeaea),
    error = Color(0xffcf6679)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff2ab7ca),//Purple40,
    secondary = Color(0xffa1c6ea),//PurpleGrey40,
   // tertiary = Pink40,

    // Other default colors to override
    background = Color(0xfff8f9fa),//Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xff2d2d2d),//Color(0xFF1C1B1F),
    onSurface = Color(0xff2d2d2d),//Color(0xFF1C1B1F),
    error = Color(0xffb00020)

)

@Composable
fun DebtTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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