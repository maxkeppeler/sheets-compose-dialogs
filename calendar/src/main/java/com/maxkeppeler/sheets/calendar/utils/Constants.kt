package com.maxkeppeler.sheets.calendar.utils

import java.time.LocalDate

/**
 * Various internal constants.
 */
internal object Constants {

    // Default values for CalendarConfig class.

    const val DEFAULT_MIN_YEAR = 1900
    val DEFAULT_MAX_YEAR = LocalDate.now().year
    const val DEFAULT_MONTH_SELECTION = false
    const val DEFAULT_YEAR_SELECTION = false


    // Constants for behaviours

    const val SUCCESS_DISMISS_DELAY = 600L


    // Constants for various indices for better readability

    internal const val RANGE_START = 0
    internal const val RANGE_END = 1
    internal const val FIRST_DAY_IN_MONTH = 1
    internal const val DAYS_IN_WEEK = 7
}