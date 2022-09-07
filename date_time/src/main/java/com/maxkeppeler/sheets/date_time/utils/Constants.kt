package com.maxkeppeler.sheets.date_time.utils

import java.time.LocalDate

/**
 * Date Time dialog specific constants.
 */
internal object Constants {

    internal const val DEFAULT_MIN_YEAR = 1900
    internal val DEFAULT_MAX_YEAR = LocalDate.now().year

    // a (am-pm-of-day)
    internal const val SYMBOL_24_HOUR_FORMAT = "a"

    // m (second-of-minute)
    internal const val SYMBOL_SECONDS = "s"

    // m (minute-of-hour)
    internal const val SYMBOL_MINUTES = "m"

    // H (hour-of-day) 0-23
    // h (clock-hour-of-am-pm) 1-12
    internal const val SYMBOL_HOUR = "H"

    // d (day-of-month)
    internal const val SYMBOL_DAY = "d"

    // M (month-of-year)
    internal const val SYMBOL_MONTH = "M"

    // y (year-of-era)
    internal const val SYMBOL_YEAR = "y"
}