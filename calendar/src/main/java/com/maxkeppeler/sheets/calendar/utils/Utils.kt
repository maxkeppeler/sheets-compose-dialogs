/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.maxkeppeler.sheets.calendar.utils

import androidx.annotation.RestrictTo
import com.maxkeppeler.sheets.calendar.models.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.temporal.WeekFields
import java.util.*

/**
 * Get week in year.
 */
internal val LocalDate.weekOfWeekBasedYear: Int
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())

/**
 * Get first day of this week.
 */
internal val LocalDate.beginOfWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        var dateNew = this
        while (dateNew.dayOfWeek != DayOfWeek.MONDAY) {
            dateNew = dateNew.minusDays(1)
        }
        return dateNew
    }

/**
 * Get first day of previous week.
 */
internal val LocalDate.previousWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val dateNew = when {
            dayOfMonth == Constants.FIRST_DAY_IN_MONTH && dayOfWeek != DayOfWeek.MONDAY -> {
                // Skips in current week to previous month's monday
                with(DayOfWeek.MONDAY)
            }
            dayOfMonth >= Constants.DAYS_IN_WEEK || dayOfMonth == Constants.FIRST_DAY_IN_MONTH
                    && dayOfWeek == DayOfWeek.MONDAY -> {
                // Skip to previous week
                minusWeeks(1)
            }
            else -> {
                // Skip to first day of the month
                withDayOfMonth(Constants.FIRST_DAY_IN_MONTH)
            }
        }
        return dateNew
    }

/**
 * Get first day of next week.
 */
internal val LocalDate.nextWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val daysInMonth = lengthOfMonth()
        val daysInMonthLeft = daysInMonth - dayOfMonth
        val daysUntilNextMonday = (Constants.DAYS_IN_WEEK - dayOfWeek.value).plus(1).toLong()
        val dateNew = when {
            dayOfMonth == Constants.FIRST_DAY_IN_MONTH -> {
                // Skip to next monday
                plusDays(daysUntilNextMonday)
            }
            daysInMonthLeft >= Constants.DAYS_IN_WEEK -> {
                // Skip to next week
                plusWeeks(1)
            }
            else -> {
                // Last day in month or less than a week of days left in month, skip to begin next month
                plusMonths(1).withDayOfMonth(Constants.FIRST_DAY_IN_MONTH)
            }
        }
        return dateNew
    }

/**
 * Get initial camera date of the selection.
 */
internal val CalendarSelection.initialCameraDate: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val cameraDateBasedOnMode = when (this) {
            is CalendarSelection.Date -> selectedDate
            is CalendarSelection.Dates -> selectedDates?.firstOrNull()
            is CalendarSelection.Period -> selectedRange?.lower
        } ?: LocalDate.now()
        return cameraDateBasedOnMode.beginOfWeek
    }

/**
 * Get selection value of date.
 */
internal val CalendarSelection.dateValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = if (this is CalendarSelection.Date) selectedDate else null

/**
 * Get selection value of dates.
 */
internal val CalendarSelection.datesValue: Array<LocalDate>
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val value = if (this is CalendarSelection.Dates) selectedDates?.toMutableList()
            ?: mutableListOf() else mutableListOf()
        return value.toTypedArray()
    }

/**
 * Get selection value of range.
 */
internal val CalendarSelection.rangeValue: Array<LocalDate?>
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val value = if (this is CalendarSelection.Period) selectedRange else null
        return mutableListOf(value?.lower, value?.upper).toTypedArray()
    }

/**
 * Get range start value.
 */
internal val List<LocalDate?>.startValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = this.getOrNull(Constants.RANGE_START)

/**
 * Get range end value.
 */
internal val List<LocalDate?>.endValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = this.getOrNull(Constants.RANGE_END)

/**
 * Calculate the month data based on the camera date and the restrictions.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcMonthData(
    config: CalendarConfig,
    cameraDate: LocalDate,
    today: LocalDate
): IntRange? {
    val months = Month.values().toMutableList()
    val newMonths = when (config.disabledTimeline) {
        CalendarTimeline.PAST -> months.filter {
            cameraDate.withMonth(it.value).let { date ->
                date.year >= config.minYear
                        || date.isAfter(today)
                        || cameraDate.month == date.month
                        || today.month == date.month
            }
        }
        CalendarTimeline.FUTURE -> months.filter {
            cameraDate.withMonth(it.value).let { date ->
                date.year <= config.minYear
                        || date.isBefore(today)
                        || cameraDate.month == date.month
                        || today.month == date.month
            }
        }
        else -> months
    }

    return if (newMonths.isNotEmpty()) IntRange(
        newMonths.first().ordinal,
        newMonths.last().value
    ) else null
}

/**
 * Calculate the calendar data based on the camera-date.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcCalendarData(config: CalendarConfig, cameraDate: LocalDate): CalendarData {
    var weekCameraDate = cameraDate
    val offsetStart = when (config.style) {
        CalendarStyle.MONTH -> cameraDate.withDayOfMonth(Constants.FIRST_DAY_IN_MONTH).dayOfWeek.ordinal
        CalendarStyle.WEEK -> {
            val dayOfWeekInMonth = cameraDate.withDayOfMonth(Constants.FIRST_DAY_IN_MONTH).dayOfWeek
            val diff = dayOfWeekInMonth.ordinal
            val value = if (weekCameraDate.dayOfMonth <= Constants.DAYS_IN_WEEK && diff > 0) {
                val offset = weekCameraDate.dayOfWeek.ordinal
                if (weekCameraDate.dayOfWeek != DayOfWeek.MONDAY) {
                    weekCameraDate = cameraDate.minusDays(offset.toLong())
                    offset
                } else weekCameraDate.dayOfWeek.ordinal
            } else weekCameraDate.dayOfWeek.ordinal
            value
        }
    }

    val days = when (config.style) {
        CalendarStyle.MONTH -> cameraDate.lengthOfMonth()
        CalendarStyle.WEEK -> DayOfWeek.values().size - offsetStart
    }

    return CalendarData(
        offsetStart = offsetStart,
        weekCameraDate = weekCameraDate,
        cameraDate = cameraDate,
        days = days,
    )
}

/**
 * Calculate the calendar date-data based on the date.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcCalendarDateData(
    date: LocalDate,
    calendarViewData: CalendarData,
    today: LocalDate,
    selection: CalendarSelection,
    config: CalendarConfig,
    selectedDate: LocalDate?,
    selectedDates: List<LocalDate>?,
    selectedRange: Pair<LocalDate?, LocalDate?>
): CalendarDateData? {

    if (date.monthValue != calendarViewData.cameraDate.monthValue) return null

    var selectedStartInit = false
    var selectedEnd = false
    var selectedBetween = false
    val selected = when (selection) {
        is CalendarSelection.Date -> selectedDate == date
        is CalendarSelection.Dates -> {
            selectedDates?.contains(date) ?: false
        }
        is CalendarSelection.Period -> {
            val selectedStart = selectedRange.first == date
            selectedStartInit = selectedStart && selectedRange.second != null
            selectedEnd = selectedRange.second == date
            selectedBetween = (selectedRange.first?.let { date.isAfter(it) } ?: false)
                    && selectedRange.second?.let { date.isBefore(it) } ?: false
            selectedBetween || selectedStart || selectedEnd
        }
    }

    val otherMonth = config.disabledTimeline?.let { timeline ->
        when (timeline) {
            CalendarTimeline.PAST -> date.isBefore(today)
            CalendarTimeline.FUTURE -> date.isAfter(today)
        }
    } ?: false
    val disabled = config.disabledDates?.contains(date) ?: false

    return CalendarDateData(
        date = date,
        disabled = disabled,
        selected = selected,
        selectedBetween = selectedBetween,
        selectedStart = selectedStartInit,
        selectedEnd = selectedEnd,
        otherMonth = otherMonth
    )
}



