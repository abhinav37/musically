package com.abhinav.musically.designsystem.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val ColorPalette = lightColors(
    primary = Color(0XFF6D4AFF),
    secondary = Color(0XFF0C0C14),
    surface = Color(0XFF0C0C14),
    background = Color(0XFFFFFFFF),
)

data class MusicallyColor(
    val primary: Color,
    val secondary: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val buttonText: Color,
    val primaryForegroundText: Color,
    val border: Color,
    val onBackground: Color,
)

// Design System Color
val DSColors = MusicallyColor(
    primary = Color(0XFF6D4AFF),
    secondary = Color(0XFF706D6B),
    primaryText = Color(0XFF0C0C14),
    secondaryText = Color(0XFF706D6B),
    primaryBackground = Color(0XFFFFFFFF),
    secondaryBackground = Color(0XFFAAAAAA),
    buttonText = Color(0XFFFFFFFF),
    primaryForegroundText = Color(0XFF727680),
    border = Color(0xFF565657),
    onBackground = Color(0xFFF0F0F0)
)
