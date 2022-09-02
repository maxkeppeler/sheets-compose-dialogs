package com.maxkeppeler.sheets.date_text.utils

import java.time.LocalDate

/**
 * Various internal constants.
 */
internal object Constants {

    const val DEFAULT_MIN_YEAR = 1900
    val DEFAULT_MAX_YEAR = LocalDate.now().year

    // a (am-pm-of-day)
    const val SYMBOL_24_HOUR_FORMAT = "a"

    // m (second-of-minute)
    const val SYMBOL_SECONDS = "s"

    // m (minute-of-hour)
    const val SYMBOL_MINUTES = "m"

    // H (hour-of-day) 0-23
    // h (clock-hour-of-am-pm) 1-12
    const val SYMBOL_HOUR = "H"

    // d (day-of-month)
    const val SYMBOL_DAY = "d"

    // M (month-of-year)
    const val SYMBOL_MONTH = "M"

    // y (year-of-era)
    const val SYMBOL_YEAR = "y"
}