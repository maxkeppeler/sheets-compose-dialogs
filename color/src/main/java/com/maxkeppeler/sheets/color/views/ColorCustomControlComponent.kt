package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.maxkeppeler.sheets.color.R

@Composable
internal fun ColorCustomControlComponent(color: Int, onColorChange: (Int) -> Unit) {

    val alphaValue = remember(color) { mutableStateOf(color.alpha) }
    val redValue = remember(color) { mutableStateOf(color.red) }
    val greenValue = remember(color) { mutableStateOf(color.green) }
    val blueValue = remember(color) { mutableStateOf(color.blue) }

    val newColor by remember(alphaValue.value, redValue.value, greenValue.value, blueValue.value) {
        mutableStateOf(Color(redValue.value, greenValue.value, blueValue.value, alphaValue.value))
    }

    LaunchedEffect(newColor) { onColorChange.invoke(newColor.toArgb()) }

    val colorItemLabelWidth = remember { mutableStateOf<Int?>(null) }
    val colorValueLabelWidth = remember { mutableStateOf<Int?>(null) }

    val colorItems = mutableListOf(
        stringResource(R.string.sheets_color_picker_alpha) to alphaValue,
        stringResource(R.string.sheets_color_picker_red) to redValue,
        stringResource(R.string.sheets_color_picker_green) to greenValue,
        stringResource(R.string.sheets_color_picker_blue) to blueValue
    )

    Column(modifier = Modifier.padding(top = 12.dp)) {
        colorItems.forEach { entry ->
            ColorCustomControlItemComponent(
                name = entry.first,
                value = entry.second.value,
                onValueChange = { entry.second.value = it },
                colorItemLabelWidth = colorItemLabelWidth,
                colorValueLabelWidth = colorValueLabelWidth,
            )
        }
    }

}
