package com.abhinav.musically.designsystem.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

// App theme inherits Material Theme
// The whole Design system package should idealy be in its own module
// But for the scope of this project its fine
@Composable
fun MusicallyTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = ColorPalette, typography = Typography(), content = content)
}
