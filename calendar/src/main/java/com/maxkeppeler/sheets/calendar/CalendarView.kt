@file:OptIn(ExperimentalSnapperApi::class)

package com.maxkeppeler.sheets.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.calendar.models.*
import com.maxkeppeler.sheets.calendar.utils.*
import com.maxkeppeler.sheets.calendar.views.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CalendarView(
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val dateSaver = Saver<MutableState<LocalDate?>, LocalDate>(
        save = { it.value }, restore = { mutableStateOf(it) }
    )
    val datesSaver = Saver<SnapshotStateList<LocalDate>, List<LocalDate>>(
        save = { it.toList() },
        restore = { mutableStateListOf(*it.toTypedArray()) }
    )
    val rangeSaver = Saver<SnapshotStateList<LocalDate?>, List<LocalDate?>>(
        save = { it.toList() },
        restore = { mutableStateListOf(*it.toTypedArray()) }
    )

    val coroutine = rememberCoroutineScope()
    var mode by rememberSaveable { mutableStateOf(CalendarDisplayMode.CALENDAR) }
    var cameraDate by rememberSaveable { mutableStateOf(selection.initialCameraDate) }
    val date = rememberSaveable(saver = dateSaver) { mutableStateOf(selection.dateValue) }
    val dates = rememberSaveable(saver = datesSaver) { mutableStateListOf(*selection.datesValue) }
    val range = rememberSaveable(saver = rangeSaver) { mutableStateListOf(*selection.rangeValue) }
    var isRangeSelectionStart by rememberSaveable { mutableStateOf(true) }

    val onInvokeListener = {
        when (selection) {
            is CalendarSelection.Date -> selection.onSelectDate(date.value!!)
            is CalendarSelection.Dates -> selection.onSelectDate(dates)
            is CalendarSelection.Period -> selection.onSelectRange(
                range.startValue!!,
                range.endValue!!
            )
        }
    }

    val onSelection: (() -> Unit) -> Unit = { selectionUnit ->
        coroutine.launch {
            delay(Constants.SUCCESS_DISMISS_DELAY)
            selectionUnit()
            onCancel()
        }
    }

    val onDateClickHandler: (LocalDate) -> Unit = { newDate ->
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
                val beforeStart = range.startValue?.let { newDate.isBefore(it) } ?: false
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
        if (!selection.withButtonView) onSelection(onInvokeListener)
    }


    val isValid: () -> Boolean = {
        when (selection) {
            is CalendarSelection.Date -> date.value != null
            is CalendarSelection.Dates -> !dates.isEmpty()
            is CalendarSelection.Period -> range.startValue != null && range.endValue != null
        }
    }

    val today = LocalDate.now()
    val prevDisabled = when (config.style) {
        CalendarStyle.MONTH -> (cameraDate.year <= today.year && cameraDate.monthValue <= today.monthValue)
                && config.disabledTimeline == CalendarTimeline.PAST
        CalendarStyle.WEEK -> (cameraDate.year <= today.year
                && cameraDate.weekOfWeekBasedYear <= today.weekOfWeekBasedYear)
                && config.disabledTimeline == CalendarTimeline.PAST
    }
    val nextDisabled = when (config.style) {
        CalendarStyle.MONTH -> (cameraDate.year >= today.year
                && cameraDate.monthValue >= today.monthValue)
                && config.disabledTimeline == CalendarTimeline.FUTURE
        CalendarStyle.WEEK -> (cameraDate.year >= today.year
                && cameraDate.weekOfWeekBasedYear >= today.weekOfWeekBasedYear)
                && config.disabledTimeline == CalendarTimeline.FUTURE
    }

    val onPrev = {
        cameraDate = when (config.style) {
            CalendarStyle.MONTH -> cameraDate.minusMonths(1)
            CalendarStyle.WEEK -> cameraDate.previousWeek
        }
    }
    val onNext = {
        cameraDate = when (config.style) {
            CalendarStyle.MONTH -> cameraDate.plusMonths(1)
            CalendarStyle.WEEK -> cameraDate.nextWeek
        }
    }
    val calendarGridState = rememberLazyGridState()
    val yearListState = rememberLazyListState()

    val cells = when (mode) {
        CalendarDisplayMode.CALENDAR -> DayOfWeek.values().size
        CalendarDisplayMode.YEAR -> 1
        CalendarDisplayMode.MONTH -> 4
    }
    val yearsRange by remember {
        val value = IntRange(config.minYear, config.maxYear.plus(1))
        mutableStateOf(value)
    }
    val monthRange by remember(cameraDate) {
        val value = calcMonthData(config, cameraDate, today)
        mutableStateOf(value)
    }
    val calendarData by remember(cameraDate) {
        val value = calcCalendarData(config, cameraDate)
        mutableStateOf(value)
    }

    LaunchedEffect(mode) {
        if (mode == CalendarDisplayMode.YEAR) {
            yearListState.scrollToItem(cameraDate.year.minus(yearsRange.first).minus(1))
        }
    }

    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {

            CalendarTopComponent(
                config = config,
                mode = mode,
                navigationDisabled = monthRange == null || mode != CalendarDisplayMode.CALENDAR,
                prevDisabled = prevDisabled,
                nextDisabled = nextDisabled,
                cameraDate = cameraDate,
                onPrev = onPrev,
                onNext = onNext,
                onMonthClick = {
                    mode = when (mode) {
                        CalendarDisplayMode.MONTH -> CalendarDisplayMode.CALENDAR
                        CalendarDisplayMode.YEAR -> CalendarDisplayMode.MONTH
                        else -> CalendarDisplayMode.MONTH
                    }
                },
                onYearClick = {
                    mode = when (mode) {
                        CalendarDisplayMode.YEAR -> CalendarDisplayMode.CALENDAR
                        CalendarDisplayMode.MONTH -> CalendarDisplayMode.YEAR
                        else -> CalendarDisplayMode.YEAR
                    }
                },
            )
//
//            val selectionModifier = when (mode) {
//                CalendarDisplayMode.CALENDAR -> Modifier.wrapContentHeight()
//                else -> Modifier.weight(1f, false)
//            }

            CalendarBaseSelectionComponent(
                modifier = Modifier.wrapContentHeight(),
                calendarGridState = calendarGridState,
                yearListState = yearListState,
                mode = mode,
                cells = cells,
                nextDisabled = nextDisabled,
                prevDisabled = prevDisabled,
                onNext = onNext,
                onPrev = onPrev,
                onCalendarView = {
                    setupCalendarSelectionView(
                        config = config,
                        cells = cells,
                        data = calendarData,
                        today = today,
                        selection = selection,
                        onSelect = onDateClickHandler,
                        selectedDate = date.value,
                        selectedDates = dates,
                        selectedRange = Pair(range.startValue, range.endValue),
                    )
                },
                onMonthView = {
                    setupMonthSelectionView(
                        monthRange = monthRange!!,
                        selectedMonth = cameraDate.month,
                        onMonthClick = {
                            cameraDate = cameraDate.withMonth(it.value).beginOfWeek
                            mode = CalendarDisplayMode.CALENDAR
                        }
                    )
                },
                onYearView = {
                    setupYearSelectionView(
                        yearsRange = yearsRange,
                        selectedYear = cameraDate.year,
                        onYearClick = {
                            cameraDate = cameraDate.withYear(it).beginOfWeek
                            mode = CalendarDisplayMode.CALENDAR
                        })
                }
            )
        }

        if (selection.withButtonView && mode == CalendarDisplayMode.CALENDAR) {
            ButtonComponent(
                negativeButtonText = selection.negativeButtonText,
                positiveButtonText = selection.positiveButtonText,
                onPositiveValid = isValid,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onInvokeListener()
                    onCancel()
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
private fun CalendarViewPreview() {
    CalendarView(
        selection = CalendarSelection.Date { selectedDate -> }
    )
}