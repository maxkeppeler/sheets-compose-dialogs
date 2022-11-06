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

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.utils.endValue
import com.maxkeppeler.sheets.calendar.utils.startValue
import com.maxkeppeler.sheets.calendar.views.*
import java.time.LocalDate

/**
 * Calendar dialog for the use-case to select a date or period in a typical calendar-view.
 * @param sheetState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun CalendarView(
    sheetState: SheetState,
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    header: Header? = null,
) {
    val calendarState = rememberCalendarState(selection, config)
    StateHandler(sheetState, calendarState)

    val coroutine = rememberCoroutineScope()
    val onSelection: (LocalDate) -> Unit = {
        calendarState.processSelection(it)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = calendarState::onFinish,
            onFinished = sheetState::finish,
            onDisableInput = calendarState::disableInput
        )
    }

    val yearListState = rememberLazyListState()
    LaunchedEffect(calendarState.mode) {
        if (calendarState.mode == CalendarDisplayMode.YEAR) {
            yearListState.scrollToItem(calendarState.yearIndex)
        }
    }

    FrameBase(
        header = header,
        content = {
            CalendarTopComponent(
                config = config,
                mode = calendarState.mode,
                navigationDisabled = calendarState.monthRange == null || calendarState.mode != CalendarDisplayMode.CALENDAR,
                prevDisabled = calendarState.isPrevDisabled,
                nextDisabled = calendarState.isNextDisabled,
                cameraDate = calendarState.cameraDate,
                onPrev = calendarState::onPrevious,
                onNext = calendarState::onNext,
                onMonthClick = { calendarState.onMonthSelectionClick() },
                onYearClick = { calendarState.onYearSelectionClick() },
            )
            CalendarBaseSelectionComponent(
                modifier = Modifier.wrapContentHeight(),
                yearListState = yearListState,
                mode = calendarState.mode,
                cells = calendarState.cells,
                onCalendarView = {
                    setupCalendarSelectionView(
                        config = config,
                        cells = calendarState.cells,
                        data = calendarState.calendarData,
                        today = calendarState.today,
                        selection = selection,
                        onSelect = onSelection,
                        selectedDate = calendarState.date.value,
                        selectedDates = calendarState.dates,
                        selectedRange = Pair(
                            calendarState.range.startValue,
                            calendarState.range.endValue
                        ),
                    )
                },
                onMonthView = {
                    setupMonthSelectionView(
                        monthRange = calendarState.monthRange!!,
                        selectedMonth = calendarState.cameraDate.month,
                        onMonthClick = calendarState::onMonthClick
                    )
                },
                onYearView = {
                    setupYearSelectionView(
                        yearsRange = calendarState.yearsRange,
                        selectedYear = calendarState.cameraDate.year,
                        onYearClick = calendarState::onYearClick
                    )
                }
            )
        },
        buttonsVisible = selection.withButtonView && calendarState.mode == CalendarDisplayMode.CALENDAR
    ) {
        ButtonsComponent(
            selection = selection,
            onPositiveValid = calendarState.valid,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = calendarState::onFinish,
            onClose = sheetState::finish
        )
    }
}