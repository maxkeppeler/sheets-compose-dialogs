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
package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.lazy.grid.LazyGridScope
import com.maxkeppeler.sheets.calendar.models.CalendarMonthData
import java.time.Month

/**
 * The view that displays all relevant year information.
 * @param monthRange The range of months.
 * @param selectedMonth The month that is currently selected.
 * @param onMonthClick The listener that is invoked when a month is selected.
 */
internal fun LazyGridScope.setupMonthSelectionView(
    monthsData: CalendarMonthData,
    onMonthClick: (Month) -> Unit,
) {
    Month.values().forEach { month ->
        val selected = monthsData.selected == month
        val disabled = monthsData.disabled.contains(month)
        val thisMonth = monthsData.thisMonth == month
        item {
            MonthItemComponent(
                month = month,
                selected = selected,
                disabled = disabled,
                thisMonth = thisMonth,
                onMonthClick = onMonthClick
            )
        }
    }
}

