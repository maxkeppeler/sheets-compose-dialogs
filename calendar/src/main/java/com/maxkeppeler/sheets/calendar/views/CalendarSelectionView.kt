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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarData
import com.maxkeppeler.sheets.calendar.models.CalendarDateData
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarViewType
import com.maxkeppeler.sheets.calendar.utils.calcCalendarDateData
import java.time.DayOfWeek
import java.time.LocalDate
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view that displays all relevant calendar information.
 * @param dayOfWeekLabels The labels for the days of the week.
 * @param cells The amount of cells / columns that are used for the calendar grid view.
 * @param config The general configuration for the dialog view.
 * @param selection The selection configuration for the dialog.
 * @param data The calculated data of the current calendar view.
 * @param today The date of today.
 * @param onSelect The listener that is invoked when a date is selected.
 * @param selectedDate The date that is currently selected.
 * @param selectedDates The dates that are currently selected.
 * @param selectedRange The range that is currently selected.
 */
internal fun LazyGridScope.setupCalendarSelectionView(
    dayOfWeekLabels: Map<DayOfWeek, String>,
    cells: Int,
    orientation: LibOrientation,
    config: CalendarConfig,
    selection: CalendarSelection,
    data: CalendarData,
    onSelect: (LocalDate) -> Unit,
    selectedDate: LocalDate?,
    selectedDates: List<LocalDate>?,
    selectedRange: Pair<LocalDate?, LocalDate?>,
) {
    val offset = if (config.displayCalendarWeeks) 1 else 0
    items(cells) { cell ->
        val label = dayOfWeekLabels.values.toList().getOrNull(cell - offset)
        label?.let { CalendarHeaderItemComponent(label) } ?: CalendarWeekHeaderItemComponent()
    }
    item(span = { GridItemSpan(cells) }) { Spacer(modifier = Modifier.height(dimensionResource(RC.dimen.scd_small_50))) }


    data.days.forEach { weekDays ->
        items(weekDays) { day ->
            when (day.first) {
                CalendarViewType.CW -> CalendarWeekItemComponent(
                    value = day.second as String,
                    selection = selection
                )

                CalendarViewType.DAY_START_OFFSET -> CalendarDateItemComponent(
                    data = CalendarDateData(otherMonth = true),
                    selection = selection,
                    orientation = orientation
                )

                CalendarViewType.DAY -> {
                    val dateData = calcCalendarDateData(
                        date = day.second as LocalDate,
                        calendarViewData = data,
                        selection = selection,
                        config = config,
                        selectedDate = selectedDate,
                        selectedDates = selectedDates,
                        selectedRange = selectedRange
                    )
                    dateData?.let {
                        CalendarDateItemComponent(
                            orientation = orientation,
                            data = dateData,
                            selection = selection,
                            onDateClick = onSelect
                        )

                    }
                }
            }
        }
    }
}
