/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
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
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testSequenceTagOf
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
        stringResource(R.string.scd_color_dialog_alpha) to alphaValue,
        stringResource(R.string.scd_color_dialog_red) to redValue,
        stringResource(R.string.scd_color_dialog_green) to greenValue,
        stringResource(R.string.scd_color_dialog_blue) to blueValue
    )

    Column(modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_small_150))) {
        colorItems.forEachIndexed { index, entry ->
            if ((config.allowCustomColorAlphaValues)
                || (!config.allowCustomColorAlphaValues && index > 0)
            ) {
                ColorCustomControlItemComponent(
                    label = entry.first,
                    value = entry.second.value,
                    onValueChange = { entry.second.value = it },
                    colorItemLabelWidth = colorItemLabelWidth,
                    colorValueLabelWidth = colorValueLabelWidth,
                    sliderTestTag = testSequenceTagOf(
                        TestTags.COLOR_CUSTOM_VALUE_SLIDER,
                        index.toString()
                    ),
                )
            }
        }
    }

}
