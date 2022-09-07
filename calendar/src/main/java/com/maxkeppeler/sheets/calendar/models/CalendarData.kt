package com.maxkeppeler.sheets.calendar.models

import java.time.LocalDate

/**
 * Defines all calculated information for the current view.
 * The current view can be [CalendarStyle.WEEK] or [CalendarStyle.MONTH].
 * @param offsetStart The amount of days before the week or month actually starts.
 * @param weekCameraDate The current camera-date of the week view.
 * @param cameraDate The current camera-date of the month view.
 * @param days The amount of days to be displayed.
 */
internal data class CalendarData(
    val offsetStart: Int,
    val weekCameraDate: LocalDate,
    val cameraDate: LocalDate,
    val days: Int,
)