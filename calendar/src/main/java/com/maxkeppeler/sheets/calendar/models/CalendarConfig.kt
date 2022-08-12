package com.maxkeppeler.sheets.calendar.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.calendar.utils.Constants
import java.time.LocalDate

/**
 * Available calendar configurations.
 */
class CalendarConfig(

    /**
     * Style of calendar.
     */
    val style: CalendarStyle = CalendarStyle.MONTH,

    /**
     * Allow selection of month in additional helper-view.
     */
    val monthSelection: Boolean = Constants.DEFAULT_MONTH_SELECTION,

    /**
     * Allow selection of year in additional helper-view.
     */
    val yearSelection: Boolean = Constants.DEFAULT_YEAR_SELECTION,

    /**
     * Min-selectable year.
     */
    val minYear: Int = Constants.DEFAULT_MIN_YEAR,

    /**
     * Max-selectable year.
     */
    val maxYear: Int = Constants.DEFAULT_MAX_YEAR,

    /**
     * Define dates that are disabled.
     */
    val disabledDates: List<LocalDate>? = null,

    /**
     * Disable a timeline.
     */
    val disabledTimeline: CalendarTimeline? = null

) : BaseConfigs()