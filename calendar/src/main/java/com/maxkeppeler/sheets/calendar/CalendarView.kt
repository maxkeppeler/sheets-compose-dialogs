package com.maxkeppeler.sheets.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.calendar.models.*
import com.maxkeppeler.sheets.calendar.utils.*
import com.maxkeppeler.sheets.calendar.views.*
import java.time.LocalDate

/**
 * Calendar dialog for the use-case to select a date or period in a typical calendar-view.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun CalendarView(
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {

    val state = rememberSaveable(
        saver = CalendarState.Saver(selection, config),
        init = { CalendarState(selection, config) }
    )
    val coroutine = rememberCoroutineScope()
    val onSelection: (LocalDate) -> Unit = {
        state.processSelection(it)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = state::onFinish,
            onFinished = onCancel,
            onDisableInput = state::disableInput
        )
    }

    val yearListState = rememberLazyListState()
    LaunchedEffect(state.mode) {
        if (state.mode == CalendarDisplayMode.YEAR) {
            yearListState.scrollToItem(state.yearIndex)
        }
    }

    FrameBase(
        header = { HeaderComponent(header) },
        content = {

            CalendarTopComponent(
                config = config,
                mode = state.mode,
                navigationDisabled = state.monthRange == null || state.mode != CalendarDisplayMode.CALENDAR,
                prevDisabled = state.isPrevDisabled,
                nextDisabled = state.isNextDisabled,
                cameraDate = state.cameraDate,
                onPrev = state::onPrevious,
                onNext = state::onNext,
                onMonthClick = { state.onMonthSelectionClick() },
                onYearClick = { state.onYearSelectionClick() },
            )

            CalendarBaseSelectionComponent(
                modifier = Modifier.wrapContentHeight(),
                yearListState = yearListState,
                mode = state.mode,
                cells = state.cells,
                nextDisabled = state.isNextDisabled,
                prevDisabled = state.isPrevDisabled,
                onNext = state::onNext,
                onPrev = state::onPrevious,
                onCalendarView = {
                    setupCalendarSelectionView(
                        config = config,
                        cells = state.cells,
                        data = state.calendarData,
                        today = state.today,
                        selection = selection,
                        onSelect = onSelection,
                        selectedDate = state.date.value,
                        selectedDates = state.dates,
                        selectedRange = Pair(state.range.startValue, state.range.endValue),
                    )
                },
                onMonthView = {
                    setupMonthSelectionView(
                        monthRange = state.monthRange!!,
                        selectedMonth = state.cameraDate.month,
                        onMonthClick = state::onMonthClick
                    )
                },
                onYearView = {
                    setupYearSelectionView(
                        yearsRange = state.yearsRange,
                        selectedYear = state.cameraDate.year,
                        onYearClick = state::onYearClick
                    )
                }
            )
        },
        buttonsVisible = selection.withButtonView && state.mode == CalendarDisplayMode.CALENDAR,
        buttons = {
            ButtonsComponent(
                selection = selection,
                onPositiveValid = state.valid,
                onNegative = { selection.onNegativeClick?.invoke() },
                onPositive = state::onFinish,
                onCancel = onCancel
            )
        }
    )
}