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
package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection


/**
 * The container of a unit that was found in the localized pattern.
 * It switches between the view and selection mode.
 * @param unit The unit of the value.
 * @param height The height of the component.
 * @param onValueChange The listener that returns the selected unit option item.
 */
@Composable
internal fun UnitContainerComponent(
    unit: UnitSelection,
    height: MutableState<Int>,
    onValueChange: (UnitOptionEntry) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val width = remember { mutableStateOf(0) }

    Row(verticalAlignment = Alignment.CenterVertically) {

        if (expanded.value) {
            SelectionContainerComponent(
                heightOffsetTopPadding = dimensionResource(RC.dimen.scd_normal_150),
                unit = unit,
                height = height,
                width = width,
                options = unit.options,
                onValueChange = {
                    onValueChange(it)
                    expanded.value = false
                })
        } else {
            ValueContainerComponent(
                unit = unit,
                width = width,
                expanded = expanded
            )
        }
    }
}