/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection


/**
 * A picker component that will build up a date or time selection.
 * @param config The general configuration for the dialog.
 * @param isDate If the current picker is used for a date.
 * @param values
 * @param onValueChange The listener that is invoked when a value was selected.
 */
@Composable
internal fun PickerComponent(
    config: DateTimeConfig,
    isDate: Boolean,
    values: List<List<Any?>>,
    onValueChange: (UnitSelection, UnitOptionEntry) -> Unit,
) {
    val height = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .onGloballyPositioned { coordinates ->
                    if (height.value < coordinates.size.height) {
                        height.value = coordinates.size.height
                    }
                },
            verticalAlignment = Alignment.Bottom
        ) {
            values.forEachIndexed { index, segments ->
                segments.forEach { segment ->
                    when (segment) {
                        is String -> PickerDateCharacterComponent(text = ",")
                        is UnitSelection -> {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                UnitContainerComponent(
                                    config = config,
                                    unit = segment,
                                    height = height,
                                    onValueChange = { onValueChange(segment, it) },
                                )
                                if (!config.hideTimeCharacters
                                    && !isDate
                                    && index < values.lastIndex
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .clip(MaterialTheme.shapes.extraSmall)
                                            .padding(horizontal = dimensionResource(R.dimen.scd_small_75))
                                            .padding(top = dimensionResource(R.dimen.scd_normal_150)),
                                        text = ":"
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}