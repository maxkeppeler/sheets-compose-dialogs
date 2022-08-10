package com.maxkeppeler.sheets.calendar.models

import java.time.LocalDate

/**
 * Defines all calculated information for the current view.
 * The current view can be [CalendarStyle.WEEK] or [CalendarStyle.MONTH].
 */
internal data class CalendarData(
    val offsetStart: Int,
    val weekCameraDate: LocalDate,
    val cameraDate: LocalDate,
    val days: Int,
)