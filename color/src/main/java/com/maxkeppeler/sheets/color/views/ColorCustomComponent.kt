package com.maxkeppeler.sheets.color.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
internal fun ColorCustomComponent(
    color: Int,
    onColorChange: (Int) -> Unit,
) {
    Column {
        ColorCustomInfoComponent(
            color = color,
            onColorChange = onColorChange,
        )
        ColorCustomControlComponent(
            color = color,
            onColorChange = onColorChange
        )
    }
}
