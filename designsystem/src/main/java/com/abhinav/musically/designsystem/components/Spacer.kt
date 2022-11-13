package com.abhinav.musically.designsystem.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    size: Dp = 16.dp,
) {
    Spacer(modifier = Modifier.requiredHeight(size))
}

@Composable
fun HorizontalSpacer(
    size: Dp = 16.dp,
) {
    Spacer(modifier = Modifier.requiredWidth(size))
}
