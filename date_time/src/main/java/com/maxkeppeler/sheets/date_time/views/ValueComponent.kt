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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The value component that is displayed if a selection was made.
 * @param unit The unit of the value.
 * @param width The width of the component.
 * @param onClick The listener that is invoked if this component was selected.
 */
@Composable
internal fun ValueComponent(
    unit: UnitSelection,
    width: MutableState<Int>,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .testTags(TestTags.DATE_TIME_VALUE_SELECTION, if(unit is UnitSelection.Hour) "Hour" else unit.javaClass.simpleName)
            .onGloballyPositioned { coordinates ->
                if (width.value < coordinates.size.width) {
                    width.value = coordinates.size.width
                }
            }
            .clip(MaterialTheme.shapes.small)
            .background(
                if (unit.value != null) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
            .clickable { onClick() }
            .padding(dimensionResource(RC.dimen.scd_normal_100)),
        text = unit.value?.label
            ?: unit.value?.labelRes?.let { stringResource(id = it) }
            ?: unit.options.last().label?.map { "  " }?.joinToString(separator = "")
            ?: unit.options.last().labelRes?.let { stringResource(id = it) }
                ?.map { "  " }?.joinToString(separator = "")!!,
        style = MaterialTheme.typography.bodyLarge
    )
}