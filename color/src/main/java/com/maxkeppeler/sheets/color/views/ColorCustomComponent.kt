package com.maxkeppeler.sheets.color.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

/**
 * The custom color picker mode.
 * @param color The color that is currently selected.
 * @param onColorChange The listener that returns a selected color.
 */
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
