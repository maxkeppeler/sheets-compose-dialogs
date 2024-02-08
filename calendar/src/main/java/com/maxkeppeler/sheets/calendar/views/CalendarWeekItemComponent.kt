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
package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The calendar week item component of the calendar view.
 * @param value The value of the week.
 * @param selection The selection configuration for the dialog view.
 */
@Composable
internal fun CalendarWeekItemComponent(
    value: String,
    selection: CalendarSelection,
) {
    val normalModifier = Modifier.aspectRatio(1f, true)
    val textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp)
    val parentModifier = when (selection) {
        is CalendarSelection.Date -> Modifier.padding(dimensionResource(RC.dimen.scd_small_25))
        is CalendarSelection.Dates -> Modifier.padding(dimensionResource(RC.dimen.scd_small_25))
        is CalendarSelection.Period -> Modifier.padding(vertical = dimensionResource(RC.dimen.scd_small_25))
    }.testTags(TestTags.CALENDAR_CW, value)
    Column(modifier = parentModifier) {
        Row(
            modifier = normalModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = value,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}