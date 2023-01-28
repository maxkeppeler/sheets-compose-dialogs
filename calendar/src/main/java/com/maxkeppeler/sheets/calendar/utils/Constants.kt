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
package com.maxkeppeler.sheets.calendar.utils

import java.time.LocalDate

/**
 * Calendar dialog specific constants.
 */
internal object Constants {

    // Default values for CalendarConfig class.

    internal const val DEFAULT_MIN_YEAR = 1900
    internal val DEFAULT_MAX_YEAR = LocalDate.now().year
    internal const val DEFAULT_MONTH_SELECTION = false
    internal const val DEFAULT_YEAR_SELECTION = false

    // Constants for various indices for better readability

    internal const val RANGE_START = 0
    internal const val RANGE_END = 1
    internal const val FIRST_DAY_IN_MONTH = 1
    internal const val DAYS_IN_WEEK = 7

    // Misc

    internal const val YEAR_GRID_COLUMNS = 1
    internal const val MONTH_GRID_COLUMNS = 4

    internal const val DATE_ITEM_DISABLED_TIMELINE_OPACITY = 0.3f
    internal const val DATE_ITEM_OPACITY = 1f
}