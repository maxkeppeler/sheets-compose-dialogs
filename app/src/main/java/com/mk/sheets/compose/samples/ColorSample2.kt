@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.maxkeppeler.sheets.color.ColorDialog
import com.maxkeppeler.sheets.color.models.*

@Composable
internal fun ColorSample2(closeSelection: () -> Unit) {

    val color = remember { mutableStateOf(Color.Red.toArgb()) }

    val templateColors = MultipleColors.ColorsInt(
        Color.Red.copy(alpha = 0.1f).toArgb(),
        Color.Red.copy(alpha = 0.3f).toArgb(),
        Color.Red.copy(alpha = 0.5f).toArgb(),
        Color.Red.toArgb(),
        Color.Green.toArgb(),
        Color.Yellow.toArgb(),
    )

    ColorDialog(
        show = true,
        selection = ColorSelection(
            selectedColor = SingleColor(color.value),
            onSelectColor = { color.value = it },
        ),
        config = ColorConfig(
            templateColors = templateColors,
            defaultDisplayMode = ColorSelectionMode.CUSTOM,
            allowCustomColorAlphaValues = false
        ),
        onClose = { closeSelection() }
    )
}