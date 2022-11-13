package com.abhinav.musically.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.abhinav.musically.designsystem.theme.DSColors
import com.abhinav.musically.designsystem.theme.DSTypography

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    showLeadingIcon: Boolean = true,
    onLeadingIconClick: (String) -> Unit = { },
    trailingIcon: Painter? = null,
    onClearIconClick: () -> Unit,
    onTrailingIconClick: () -> Unit = { },
    onSearch: (KeyboardActionScope.() -> Unit)? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        shape = RoundedCornerShape(2.dp),
        color = DSColors.onBackground,
        border = BorderStroke(2.dp, DSColors.border)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardActions = KeyboardActions(onSearch = onSearch),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            singleLine = true,
            cursorBrush = SolidColor(DSColors.border),
            textStyle = DSTypography.body1Medium
        ) { innerTextField ->
            Row(modifier = Modifier.height(SEARCH_INPUT_HEIGHT),
                verticalAlignment = Alignment.CenterVertically) {
                if (showLeadingIcon) {
                    SearchIcon(onClick = { onLeadingIconClick(value) })
                } else {
                    HorizontalSpacer()
                }

                Box(modifier = Modifier.weight(1f)) {
                    innerTextField()
                    // placeholder
                    if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                        Text(
                            text = placeholder,
                            color = DSColors.primaryForegroundText,
                            style = DSTypography.body1Medium,
                            maxLines = 1
                        )
                    }
                }

                HorizontalSpacer()

                if (value.isNotEmpty() && isFocused) {
                    ClearIcon(
                        onClick = onClearIconClick,
                        modifier = Modifier.size(SEARCH_INPUT_HEIGHT)
                    )
                }

                trailingIcon?.let { TrailingIcon(it, onTrailingIconClick) }
            }
        }
    }
}

@Composable
private fun SearchIcon(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(SEARCH_INPUT_HEIGHT)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = DSColors.primaryForegroundText
        )
    }
}

@Composable
private fun TrailingIcon(
    icon: Painter,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick, modifier = Modifier.size(SEARCH_INPUT_HEIGHT)) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = DSColors.primaryForegroundText
        )
    }
}

private val SEARCH_INPUT_HEIGHT = 36.dp
