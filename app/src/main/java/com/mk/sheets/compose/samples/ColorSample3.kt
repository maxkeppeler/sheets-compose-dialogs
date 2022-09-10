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
internal fun ColorSample3(closeSelection: () -> Unit) {

    val color = remember { mutableStateOf(Color.Red.toArgb()) }

    ColorDialog(
        show = true,
        selection = ColorSelection(
            selectedColor = SingleColor(color.value),
            onSelectColor = { color.value = it },
        ),
        config = ColorConfig(
            displayMode = ColorSelectionMode.CUSTOM,
        ),
        onClose = { closeSelection() }
    )
}