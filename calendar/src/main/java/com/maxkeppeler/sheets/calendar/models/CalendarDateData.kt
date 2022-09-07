package com.maxkeppeler.sheets.calendar.models

import java.time.LocalDate

/**
 * Defines all calculated information for a specific date.
 * @param date The current date that the view represents.
 * @param disabled Whenever the date is disabled.
 * @param selected Whenever the date is selected in a single or multiple date selection.
 * @param selectedBetween Whenever the date is within a range selection.
 * @param selectedStart Whenever the date is the start of a range selection.
 * @param selectedEnd Whenever the date is the end of a range selection.
 * @param otherMonth Whenever the date lays in another month.
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