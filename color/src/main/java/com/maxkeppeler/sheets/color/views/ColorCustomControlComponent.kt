package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.core.R as RC

/**
 * The control component to build up a custom color.
 * @param color The color that is currently selected.
 * @param onColorChange The listener that returns a selected color.
 */
@Composable
internal fun ColorCustomControlComponent(
    color: Int,
    onColorChange: (Int) -> Unit
) {

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
        stringResource(R.string.scd_color_dialog_alpha) to alphaValue,
        stringResource(R.string.scd_color_dialog_red) to redValue,
        stringResource(R.string.scd_color_dialog_green) to greenValue,
        stringResource(R.string.scd_color_dialog_blue) to blueValue
    )

    Column(modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_small_150))) {
        colorItems.forEach { entry ->
            ColorCustomControlItemComponent(
                label = entry.first,
                value = entry.second.value,
                onValueChange = { entry.second.value = it },
                colorItemLabelWidth = colorItemLabelWidth,
                colorValueLabelWidth = colorValueLabelWidth,
            )
        }
    }

}
