package com.maxkeppeler.sheets.calendar.models

import java.time.LocalDate

/**
 * Defines all calculated information for a specific date.
 */
internal data class CalendarDateData(
    val date: LocalDate? = null,
    val disabled: Boolean = false,
    val selected: Boolean = false,
    val selectedBetween: Boolean = false,
    val selectedStart: Boolean = false,
    val selectedEnd: Boolean = false,
    val otherMonth: Boolean = false,
)