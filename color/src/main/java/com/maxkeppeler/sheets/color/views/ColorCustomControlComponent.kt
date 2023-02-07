/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testSequenceTagOf
import com.maxkeppeker.sheets.core.views.Grid
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.core.R as RC

/**
 * The control component to build up a custom color.
 * @param config The general configuration for the dialog view.
 * @param color The color that is currently selected.
 * @param onColorChange The listener that returns a selected color.
 */
@Composable
internal fun ColorCustomControlComponent(
    config: ColorConfig,
    orientation: LibOrientation,
    color: Int,
    onColorChange: (Int) -> Unit
) {

    val alphaValue = remember(color) {
        val value = if (config.allowCustomColorAlphaValues) color.alpha else 255
        mutableStateOf(value)
    }
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
        if (config.allowCustomColorAlphaValues) stringResource(R.string.scd_color_dialog_alpha) to alphaValue else null,
        stringResource(R.string.scd_color_dialog_red) to redValue,
        stringResource(R.string.scd_color_dialog_green) to greenValue,
        stringResource(R.string.scd_color_dialog_blue) to blueValue
    ).filterNotNull()

    Grid(
        modifier = Modifier.padding(
            top = dimensionResource(RC.dimen.scd_normal_100)
        ),
        items = colorItems,
        columns = when (orientation) {
            LibOrientation.PORTRAIT -> 1
            LibOrientation.LANDSCAPE -> 2
        },
        rowSpacing = 0.dp,
        columnSpacing = 24.dp
    ) { entry ->
        val index = colorItems.indexOf(entry)
        val onValueChange: (Int) -> Unit = { entry.second.value = it }
        val sliderTestTag = testSequenceTagOf(
            TestTags.COLOR_CUSTOM_VALUE_SLIDER,
            index.toString()
        )
        when (orientation) {
            LibOrientation.PORTRAIT ->
                ColorCustomControlListItemComponent(
                    label = entry.first,
                    value = entry.second.value,
                    onValueChange = onValueChange,
                    colorItemLabelWidth = colorItemLabelWidth,
                    colorValueLabelWidth = colorValueLabelWidth,
                    sliderTestTag = sliderTestTag,
                )
            LibOrientation.LANDSCAPE ->
                ColorCustomControlGridItemComponent(
                    label = entry.first,
                    value = entry.second.value,
                    onValueChange = onValueChange,
                    sliderTestTag = sliderTestTag,
                )
        }


    }
}
