package com.example.pasteleria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PasteleriaColorScheme = lightColorScheme(
    primary = RosaPastel,
    secondary = MarronSuave,
    background = Crema,
    surface = Blanco,
    onPrimary = Blanco,
    onSecondary = Color.White,
    onBackground = MarronSuave,
    onSurface = MarronSuave
)

@Composable
fun PasteleriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PasteleriaColorScheme,
        typography = Typography,
        content = content
    )
}
