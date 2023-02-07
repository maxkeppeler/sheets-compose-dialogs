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


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.core.R

/**
 * The control item component to build up a value of a custom color.
 * @param label The label text of the color value.
 * @param value The value of the color value.
 * @param onValueChange The listener that returns the new value of the color value.
 * @param colorItemLabelWidth The width of the label.
 * @param colorValueLabelWidth The width of the value.
 * @param sliderTestTag The text that is used for the test tag.
 */
@Composable
internal fun ColorCustomControlGridItemComponent(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    sliderTestTag: String,
) {
    val wrapOrFixedModifier: @Composable (MutableState<Int?>) -> Modifier = { stateWidth ->
        val defaultModifier = Modifier
            .wrapContentWidth()
            .onGloballyPositioned { coordinates ->
                if ((stateWidth.value ?: 0) < coordinates.size.width) {
                    stateWidth.value = coordinates.size.width
                }
            }
        stateWidth.value?.let {
            Modifier.width(LocalDensity.current.run { it.toDp() })
        } ?: defaultModifier
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End,
            )
        }
        Slider(
            modifier = Modifier
                .testTag(sliderTestTag)
                .fillMaxWidth(),
            valueRange = 0f..255f,
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
        )
    }
}

@Composable
internal fun ColorCustomControlListItemComponent(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    colorItemLabelWidth: MutableState<Int?>,
    colorValueLabelWidth: MutableState<Int?>,
    sliderTestTag: String,
) {

    val wrapOrFixedModifier: @Composable (MutableState<Int?>) -> Modifier = { stateWidth ->
        val defaultModifier = Modifier
            .wrapContentWidth()
            .onGloballyPositioned { coordinates ->
                if ((stateWidth.value ?: 0) < coordinates.size.width) {
                    stateWidth.value = coordinates.size.width
                }
            }
        stateWidth.value?.let {
            Modifier.width(LocalDensity.current.run { it.toDp() })
        } ?: defaultModifier
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = wrapOrFixedModifier(colorItemLabelWidth),
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )

        Slider(
            modifier = Modifier
                .testTag(sliderTestTag)
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.scd_normal_100)),
            valueRange = 0f..255f,
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
        )

        Text(
            modifier = wrapOrFixedModifier(colorValueLabelWidth),
            text = value.toString(),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
        )
    }
}