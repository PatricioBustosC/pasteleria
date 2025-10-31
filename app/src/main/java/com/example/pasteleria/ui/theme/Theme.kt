package com.example.pasteleria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PasteleriaColors = lightColorScheme(
    primary = ChocolateOscuro,
    onPrimary = Blanco,
    secondary = RosaPastel,
    onSecondary = Blanco,
    background = CremaFondo,
    onBackground = TextoMarron,
    surface = Blanco,
    onSurface = TextoMarron
)

@Composable
fun PasteleriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PasteleriaColors,
        typography = Typography,
        content = content
    )
}
