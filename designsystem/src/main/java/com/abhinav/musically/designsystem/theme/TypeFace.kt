package com.abhinav.musically.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abhinav.musically.designsystem.R

private val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_medium, FontWeight.Light),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

data class MusicallyTypography(
    val headline: TextStyle,
    val body1Medium: TextStyle,
    val body1Regular: TextStyle,
    val body2Medium: TextStyle,
    val body2Regular: TextStyle,
)

val DSTypography = MusicallyTypography(
    headline = TextStyle(
        fontFamily = Roboto,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ), body1Medium = TextStyle(
        fontFamily = Roboto,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ), body1Regular = TextStyle(
        fontFamily = Roboto,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ), body2Medium = TextStyle(
        fontFamily = Roboto,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ), body2Regular = TextStyle(
        fontFamily = Roboto,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ))
