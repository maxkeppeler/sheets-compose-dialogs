/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarData
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import com.maxkeppeler.sheets.calendar.models.CalendarMonthData
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.calendar.utils.Constants
import com.maxkeppeler.sheets.calendar.utils.calcCalendarData
import com.maxkeppeler.sheets.calendar.utils.calcMonthData
import com.maxkeppeler.sheets.calendar.utils.dateValue
import com.maxkeppeler.sheets.calendar.utils.datesValue
import com.maxkeppeler.sheets.calendar.utils.endOfMonth
import com.maxkeppeler.sheets.calendar.utils.endOfWeek
import com.maxkeppeler.sheets.calendar.utils.endValue
import com.maxkeppeler.sheets.calendar.utils.getInitialCameraDate
import com.maxkeppeler.sheets.calendar.utils.getInitialCustomCameraDate
import com.maxkeppeler.sheets.calendar.utils.jumpNext
import com.maxkeppeler.sheets.calendar.utils.jumpPrev
import com.maxkeppeler.sheets.calendar.utils.rangeValue
import com.maxkeppeler.sheets.calendar.utils.startOfMonth
import com.maxkeppeler.sheets.calendar.utils.startOfWeek
import com.maxkeppeler.sheets.calendar.utils.startOfWeekOrMonth
import com.maxkeppeler.sheets.calendar.utils.startValue
import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

/**
 * Handles the calendar state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class CalendarState(
    val selection: CalendarSelection,
    val config: CalendarConfig,
    stateData: CalendarStateData? = null,
) : BaseTypeState() {

    val today by mutableStateOf(LocalDate.now())
    var mode by mutableStateOf(stateData?.mode ?: CalendarDisplayMode.CALENDAR)
    var cameraDate by mutableStateOf(
        stateData?.cameraDate
            ?: getInitialCustomCameraDate(config.cameraDate, config.boundary)
            ?: getInitialCameraDate(selection, config.boundary)
    )
    var date = mutableStateOf(stateData?.date ?: selection.dateValue)
    var dates = mutableStateListOf(*(stateData?.dates ?: selection.datesValue))
    var range = mutableStateListOf(*(stateData?.range ?: selection.rangeValue))
    var isRangeSelectionStart by mutableStateOf(stateData?.rangeSelectionStart ?: true)
    var yearsRange by mutableStateOf(getInitYearsRange())
    var monthsData by mutableStateOf(getInitMonthsData())
    var calendarData by mutableStateOf(getInitCalendarData())
    var valid by mutableStateOf(isValid())

    init {
        checkSetup()
    }

    private fun checkSetup() {
        val selection = mutableListOf<LocalDate>()
        when (this.selection) {
            is CalendarSelection.Date -> date.value?.let { selection.add(it) }
            is CalendarSelection.Dates -> selection.addAll(dates)
            is CalendarSelection.Period -> {
                range.startValue?.let { selection.add(it) }
                range.endValue?.let { selection.add(it) }
            }
        }
        val overlapDate = selection.firstOrNull { config.disabledDates?.contains(it) == true }
        overlapDate?.let { throw IllegalStateException("Please correct your setup. Your selection overlaps with a provided disabled date. $it") }

        if (selection.any { it !in config.boundary }) {
            throw IllegalStateException("Please correct your setup. Your selection is out of the provided boundary. Selection: $selection, Boundary: ${config.boundary}")
        }
    }

    private fun getInitYearsRange(): ClosedRange<Int> =
        config.boundary.start.year..config.boundary.endInclusive.year

    private fun getInitMonthsData(): CalendarMonthData =
        calcMonthData(config, cameraDate)

    private fun getInitCalendarData(): CalendarData {
        return calcCalendarData(config, cameraDate)
    }

    private fun checkValid() {
        valid = isValid()
    }

    private fun refreshData() {
        yearsRange = getInitYearsRange()
        monthsData = getInitMonthsData()
        calendarData = getInitCalendarData()
    }

    private fun isValid(): Boolean = when (selection) {
        is CalendarSelection.Date -> date.value != null
        is CalendarSelection.Dates -> !dates.isEmpty()
        is CalendarSelection.Period -> range.startValue != null && range.endValue != null
    }

    val isPrevDisabled: Boolean
        get() {
            val prevCameraDate = cameraDate.jumpPrev(config)
            return when (config.style) {
                CalendarStyle.MONTH -> {
                    val isPrevOutOfBoundary =
                        prevCameraDate.isBefore(config.boundary.start.startOfMonth)
                    isPrevOutOfBoundary
                }

                CalendarStyle.WEEK -> {
                    val isPrevOutOfBoundary =
                        prevCameraDate.isBefore(config.boundary.start.startOfWeek)
                    isPrevOutOfBoundary
                }
            }
        }

    val isNextDisabled: Boolean
        get() {
            val nextCameraDate = cameraDate.jumpNext(config)
            return when (config.style) {
                CalendarStyle.MONTH -> {
                    val isNextOutOfBoundary =
                        nextCameraDate.isAfter(config.boundary.endInclusive.endOfMonth)
                    isNextOutOfBoundary
                }

                CalendarStyle.WEEK -> {
                    val isNextOutOfBoundary =
                        nextCameraDate.isAfter(config.boundary.endInclusive.endOfWeek)
                    isNextOutOfBoundary
                }
            }
        }

    val isMonthSelectionEnabled: Boolean
        get() = monthsData.disabled.size < 11 // At least 2 months

    val isYearSelectionEnabled: Boolean
        get() {
            val years = yearsRange.endInclusive.minus(yearsRange.start).plus(1)
            return years > 1 // at least 2 years
        }

    val cells: Int
        get() = when (mode) {
            CalendarDisplayMode.CALENDAR -> DayOfWeek.values().size + if (config.displayCalendarWeeks) 1 else 0
            CalendarDisplayMode.YEAR -> Constants.YEAR_GRID_COLUMNS
            CalendarDisplayMode.MONTH -> Constants.MONTH_GRID_COLUMNS
        }

    val yearIndex: Int
        get() = cameraDate.year.minus(yearsRange.start)

    fun onPrevious() {
        cameraDate = cameraDate.jumpPrev(config)
        refreshData()
    }

    fun onNext() {
        cameraDate = cameraDate.jumpNext(config)
        refreshData()
    }

    fun onMonthSelectionClick() {
        mode = when (mode) {
            CalendarDisplayMode.MONTH -> CalendarDisplayMode.CALENDAR
            CalendarDisplayMode.YEAR -> CalendarDisplayMode.MONTH
            else -> CalendarDisplayMode.MONTH
        }
    }

    fun onYearSelectionClick() {
        mode = when (mode) {
            CalendarDisplayMode.YEAR -> CalendarDisplayMode.CALENDAR
            CalendarDisplayMode.MONTH -> CalendarDisplayMode.YEAR
            else -> CalendarDisplayMode.YEAR
        }
    }

    fun onMonthClick(month: Month) {
        cameraDate = cameraDate.withMonth(month.value).startOfWeekOrMonth
        mode = CalendarDisplayMode.CALENDAR
        refreshData()
    }

    fun onYearClick(year: Int) {
        var newDate = cameraDate.withYear(year)
        // Check if current new date would be within the boundary otherwise reset to month within boundary
        newDate = when {
            newDate.isBefore(config.boundary.start) ->
                newDate.withMonth(config.boundary.start.monthValue)
                    .withDayOfMonth(config.boundary.start.dayOfMonth)

            newDate.isAfter(config.boundary.endInclusive) ->
                newDate.withMonth(config.boundary.endInclusive.monthValue)
                    .withDayOfMonth(config.boundary.endInclusive.dayOfMonth)

            else -> newDate
        }
        cameraDate = newDate.startOfWeek
        mode = CalendarDisplayMode.CALENDAR
        refreshData()
    }

    fun processSelection(newDate: LocalDate) {
        when (selection) {
            is CalendarSelection.Date -> {
                date.value = newDate
            }

            is CalendarSelection.Dates -> {
                if (dates.contains(newDate)) {
                    dates.remove(newDate)
                } else {
                    dates.add(newDate)
                }
            }

            is CalendarSelection.Period -> {
                // Check if the selected range includes any disabled dates
                val includesDisabledDate = range.startValue?.let { startDate ->
                    config.disabledDates?.any { disabledDate ->
                        disabledDate.isAfter(startDate) && disabledDate.isBefore(newDate) || disabledDate == newDate
                    }
                } ?: false

                // Reset the range if the selection includes a disabled date
                if (includesDisabledDate) {
                    range[Constants.RANGE_START] = newDate
                    range[Constants.RANGE_END] = null
                    return
                }

                // Check if the selection is the start or the date is before the start
                val beforeStart = range.startValue?.let { newDate.isBefore(it) } ?: false
                if (isRangeSelectionStart || beforeStart) {
                    // Reset the range if the selection includes a disabled date
                    range[Constants.RANGE_START] = newDate
                    range[Constants.RANGE_END] = null
                    isRangeSelectionStart = false
                } else {
                    // Check if the selection is the end or the date is after the end
                    range[Constants.RANGE_END] = newDate
                    isRangeSelectionStart = true
                }
            }
        }
        checkValid()
    }

    fun onFinish() {
        when (selection) {
            is CalendarSelection.Date -> selection.onSelectDate(date.value!!)
            is CalendarSelection.Dates -> selection.onSelectDates(dates)
            is CalendarSelection.Period -> selection.onSelectRange(
                range.startValue!!, range.endValue!!
            )
        }
    }

    override fun reset() {
        date.value = selection.dateValue
        dates.clear()
        dates.addAll(selection.datesValue)
        range.clear()
        range.addAll(selection.rangeValue)
    }

    companion object {
        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: CalendarSelection,
            config: CalendarConfig
        ): Saver<CalendarState, *> = Saver(
            save = { state ->
                CalendarStateData(
                    mode = state.mode,
                    cameraDate = state.cameraDate,
                    date = state.date.value,
                    dates = state.dates.toTypedArray(),
                    range = state.range.toTypedArray(),
                    rangeSelectionStart = state.isRangeSelectionStart
                )
            },
            restore = { data ->
                CalendarState(selection, config, data)
            }
        )
    }


    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class CalendarStateData(
        val mode: CalendarDisplayMode,
        val cameraDate: LocalDate,
        val date: LocalDate?,
        val dates: Array<LocalDate>,
        val range: Array<LocalDate?>,
        val rangeSelectionStart: Boolean
    ) : Serializable {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CalendarStateData

            if (mode != other.mode) return false
            if (cameraDate != other.cameraDate) return false
            if (date != other.date) return false
            if (!dates.contentEquals(other.dates)) return false
            if (!range.contentEquals(other.range)) return false
            if (rangeSelectionStart != other.rangeSelectionStart) return false

            return true
        }

        override fun hashCode(): Int {
            var result = mode.hashCode()
            result = 31 * result + cameraDate.hashCode()
            result = 31 * result + (date?.hashCode() ?: 0)
            result = 31 * result + dates.contentHashCode()
            result = 31 * result + range.contentHashCode()
            result = 31 * result + rangeSelectionStart.hashCode()
            return result
        }
    }
}

/**
 * Create a CalendarState and remember it.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberCalendarState(
    selection: CalendarSelection,
    config: CalendarConfig,
): CalendarState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = CalendarState.Saver(selection, config),
    init = { CalendarState(selection, config) }
)