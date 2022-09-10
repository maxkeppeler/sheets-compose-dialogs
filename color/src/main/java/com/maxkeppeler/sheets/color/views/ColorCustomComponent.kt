package com.maxkeppeler.sheets.color.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.color.models.ColorConfig

/**
 * The custom color picker mode.
 * @param config The general configuration for the dialog view.
 * @param color The color that is currently selected.
 * @param onColorChange The listener that returns a selected color.
 */
@Composable
internal fun ColorCustomComponent(
    config: ColorConfig,
    color: Int,
    onColorChange: (Int) -> Unit,
) {
    Column {
        ColorCustomInfoComponent(
            color = color,
            onColorChange = onColorChange,
        )
        ColorCustomControlComponent(
            config = config,
            color = color,
            onColorChange = onColorChange
        )
    }
}
