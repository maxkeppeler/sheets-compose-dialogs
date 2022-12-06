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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The container view that builds up the value view. It consists of the label and the value.
 * @param config The general configuration for the dialog view.
 * @param unit The unit of the value.
 * @param width The width of the component.
 * @param expanded If the value-picker is displayed.
 */
@Composable
internal fun ValueContainerComponent(
    config: DateTimeConfig,
    unit: UnitSelection,
    width: MutableState<Int>,
    expanded: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .padding(vertical = dimensionResource(RC.dimen.scd_normal_150))
            .padding(horizontal = dimensionResource(RC.dimen.scd_small_100)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitLabel(unit = unit)
        Box {
            ValueComponent(
                unit = unit,
                width = width,
                onClick = { expanded.value = true }
            )
            if (unit.value == null) {
                ValueEmptyOverlayComponent(
                    config = config,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
