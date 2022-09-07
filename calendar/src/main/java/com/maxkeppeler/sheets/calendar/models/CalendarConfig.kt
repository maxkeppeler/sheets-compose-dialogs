package com.maxkeppeler.sheets.calendar.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.calendar.utils.Constants
import java.time.LocalDate

/**
 * The general configuration for the calendar dialog.
 * @param style The style of the calendar.
 * @param monthSelection Allow the direct selection of a month.
 * @param yearSelection Allow the direct selection of a year.
 * @param minYear The minimum year that is selectable.
 * @param maxYear The maximum year that is selectable.
 * @param disabledDates A list of dates that will be marked as disabled and can not be selected.
 * @param disabledTimeline The timeline you want to disable and which dates can not be selected.
 */
class CalendarConfig(
    val style: CalendarStyle = CalendarStyle.MONTH,
    val monthSelection: Boolean = Constants.DEFAULT_MONTH_SELECTION,
    val yearSelection: Boolean = Constants.DEFAULT_YEAR_SELECTION,
    val minYear: Int = Constants.DEFAULT_MIN_YEAR,
    val maxYear: Int = Constants.DEFAULT_MAX_YEAR,
    val disabledDates: List<LocalDate>? = null,
    val disabledTimeline: CalendarTimeline? = null
) : BaseConfigs()