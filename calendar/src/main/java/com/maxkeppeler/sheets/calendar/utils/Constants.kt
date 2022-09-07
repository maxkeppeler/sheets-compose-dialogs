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
}