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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.date_time.utils.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.FormatStyle
import java.util.*
import com.maxkeppeler.sheets.core.R as RC

/**
 * A picker component that will build up a date or time selection.
 * @param config The general configuration for the dialog.
 * @param locale The locale that is used for the date and time format.
 * @param formatStyle The style of the date or time format.
 * @param onDateValueChange The listener that returns the selected date.
 * @param onTimeValueChange The listener that returns the selected time.
 */
@Composable
internal fun PickerComponent(
    config: DateTimeConfig,
    locale: Locale = Locale.getDefault(),
    formatStyle: FormatStyle,
    onDateValueChange: ((LocalDate?) -> Unit)? = null,
    onTimeValueChange: ((LocalTime?) -> Unit)? = null
) {

    val isDate = onDateValueChange != null
    val height = remember { mutableStateOf(0) }

    val pattern by remember {
        val value = getLocalizedPattern(isDate, formatStyle, locale)
        mutableStateOf(value)
    }

    val values = remember(pattern) {
        val values = getLocalizedValues(pattern)
        mutableStateListOf(*values)
    }

    var day by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }
    var month by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }
    var year by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }

    var sec by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }
    var min by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }
    var hour by rememberSaveable { mutableStateOf<UnitOptionEntry?>(null) }
    var isAm by rememberSaveable { mutableStateOf(if (is24HourFormat(pattern)) null else true) }

    if (isDate) {
        LaunchedEffect(day, month, year) {
            val valid = day != null && month != null && year != null
            if (valid) {
                val date = getLocalDateOf(day, month, year)
                date?.let { onDateValueChange?.invoke(date) }
                    ?: run { day = null } // Reset day field to provoke new selection
            } else onDateValueChange?.invoke(null)
        }
    } else {
        LaunchedEffect(sec, min, hour) {
            val secondsValid = !containsSeconds(pattern) || sec != null
            val valid = secondsValid && min != null && hour != null
            val time = if (valid) getLocalTimeOf(isAm, sec, min, hour) else null
            onTimeValueChange?.invoke(time)
        }
    }

    val onValueSelection: (UnitSelection, UnitOptionEntry) -> Unit = { unit, entry ->
        when (unit) {
            is UnitSelection.Day -> day = entry
            is UnitSelection.Month -> month = entry
            is UnitSelection.Year -> year = entry
            is UnitSelection.Hour -> hour = entry
            is UnitSelection.Minute -> min = entry
            is UnitSelection.Second -> sec = entry
            else -> Unit
        }
    }

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
            values.forEachIndexed { index, textPart ->
                val segments = getLocalizedValueSegments(textPart)
                segments.forEach { segment ->
                    if (!config.hideDateCharacters && segment.isEmpty()) {
                        PickerDateCharacterComponent(text = ",")
                    } else {
                        val unitSelection by remember(day, month, year, sec, min, hour) {
                            val unitValue = detectUnit(
                                config = config, pattern = pattern, segment = segment,
                                sec = sec, min = min, hour = hour,
                                day = day, month = month, year = year
                            )
                            mutableStateOf(unitValue)
                        }
                        unitSelection?.let { unit ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                UnitContainerComponent(
                                    unit = unit,
                                    height = height,
                                    onValueChange = { onValueSelection(unit, it) },
                                )
                                if (!config.hideTimeCharacters && !isDate
                                    && index < values.lastIndex
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .clip(MaterialTheme.shapes.extraSmall)
                                            .padding(horizontal = dimensionResource(RC.dimen.scd_small_75))
                                            .padding(top = dimensionResource(RC.dimen.scd_normal_150)),
                                        text = ":"
                                    )
                                }
                            }
                        }
                    }
                }
            }
            isAm?.let {
                UnitContainerComponent(
                    unit = UnitSelection.AmPm(),
                    height = height,
                    onValueChange = {
                        isAm = it.value == 0
                    }
                )
            }
        }
    }
}