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
package com.maxkeppeler.sheets.calendar

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.calendar.models.*
import com.maxkeppeler.sheets.calendar.utils.*
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
    var cameraDate by mutableStateOf(stateData?.cameraDate ?: selection.initialCameraDate)
    var date = mutableStateOf(stateData?.date ?: selection.dateValue)
    var dates = mutableStateListOf(*(stateData?.dates ?: selection.datesValue))
    var range = mutableStateListOf(*(stateData?.range ?: selection.rangeValue))
    var isRangeSelectionStart by mutableStateOf(stateData?.rangeSelectionStart ?: true)
    var yearsRange by mutableStateOf(getInitYearsRange())
    var monthRange by mutableStateOf(getInitMonthsRange())
    var calendarData by mutableStateOf(getInitCalendarData())
    var valid by mutableStateOf(isValid())

    private fun getInitYearsRange(): IntRange =
        IntRange(config.minYear, config.maxYear.plus(1))

    private fun getInitMonthsRange(): IntRange? {
        val today = LocalDate.now()
        return calcMonthData(config, cameraDate, today)
    }

    private fun getInitCalendarData(): CalendarData {
        return calcCalendarData(config, cameraDate)
    }

    private fun checkValid() {
        valid = isValid()
    }

    private fun refreshData() {
        yearsRange = getInitYearsRange()
        monthRange = getInitMonthsRange()
        calendarData = getInitCalendarData()
    }

    private fun isValid(): Boolean = when (selection) {
        is CalendarSelection.Date -> date.value != null
        is CalendarSelection.Dates -> !dates.isEmpty()
        is CalendarSelection.Period -> range.startValue != null && range.endValue != null
    }

    val isPrevDisabled: Boolean
        get() {
            val today = LocalDate.now()
            return when (config.style) {
                CalendarStyle.MONTH -> (cameraDate.year <= today.year && cameraDate.monthValue <= today.monthValue)
                        && config.disabledTimeline == CalendarTimeline.PAST
                CalendarStyle.WEEK -> (cameraDate.year <= today.year
                        && cameraDate.weekOfWeekBasedYear <= today.weekOfWeekBasedYear)
                        && config.disabledTimeline == CalendarTimeline.PAST
            }
        }

    val isNextDisabled: Boolean
        get() = when (config.style) {
            CalendarStyle.MONTH -> (cameraDate.year >= today.year
                    && cameraDate.monthValue >= today.monthValue)
                    && config.disabledTimeline == CalendarTimeline.FUTURE
            CalendarStyle.WEEK -> (cameraDate.year >= today.year
                    && cameraDate.weekOfWeekBasedYear >= today.weekOfWeekBasedYear)
                    && config.disabledTimeline == CalendarTimeline.FUTURE
        }

    val cells: Int
        get() = when (mode) {
            CalendarDisplayMode.CALENDAR -> DayOfWeek.values().size
            CalendarDisplayMode.YEAR -> Constants.YEAR_GRID_COLUMNS
            CalendarDisplayMode.MONTH -> Constants.MONTH_GRID_COLUMNS
        }

    val yearIndex: Int
        get() = cameraDate.year.minus(yearsRange.first)

    fun onPrevious() {
        cameraDate = when (config.style) {
            CalendarStyle.MONTH -> cameraDate.minusMonths(1)
            CalendarStyle.WEEK -> cameraDate.previousWeek
        }
        refreshData()
    }

    fun onNext() {
        cameraDate = when (config.style) {
            CalendarStyle.MONTH -> cameraDate.plusMonths(1)
            CalendarStyle.WEEK -> cameraDate.nextWeek
        }
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
        cameraDate = cameraDate.withMonth(month.value).beginOfWeek
        mode = CalendarDisplayMode.CALENDAR
        refreshData()
    }

    fun onYearClick(year: Int) {
        cameraDate = cameraDate.withYear(year).beginOfWeek
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
                val beforeStart =
                    range.startValue?.let { newDate.isBefore(it) } ?: false
                val containsDisabledDate = range.endValue?.let { startDate ->
                    config.disabledDates?.any { it.isAfter(startDate) && it.isBefore(newDate) }
                } ?: false
                if (isRangeSelectionStart || beforeStart || containsDisabledDate) {
                    range[Constants.RANGE_START] = newDate
                    range[Constants.RANGE_END] = null
                    isRangeSelectionStart = false
                } else {
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
        date.value = null
        dates.clear()
        range.clear()
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