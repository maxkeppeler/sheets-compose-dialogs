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

import androidx.compose.foundation.lazy.LazyListScope
import java.time.LocalDate

/**
 * The view that displays all relevant year information.
 * @param yearsRange The range of years
 * @param selectedYear The year that is currently selected.
 * @param onYearClick The listener that is invoked when a year is selected.
 */
internal fun LazyListScope.setupYearSelectionView(
    yearsRange: ClosedRange<Int>,
    selectedYear: Int,
    onYearClick: (Int) -> Unit
) {
    items(yearsRange.endInclusive.minus(yearsRange.start).plus(1)) {
        val year = yearsRange.start + it
        val selected = selectedYear == year
        val thisYear = year == LocalDate.now().year
        YearItemComponent(
            year = year,
            thisYear = thisYear,
            selected = selected,
            onYearClick = onYearClick
        )
    }
}