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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.date_time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.date_time.views.PickerComponent

/**
 * Date Time dialog for the use-case to select a date, time or both in a quick way.
 * @param sheetState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun DateTimeView(
    sheetState: SheetState,
    selection: DateTimeSelection,
    config: DateTimeConfig = DateTimeConfig(),
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()
    val dateTimeState = rememberDateTimeState(selection, config)
    StateHandler(sheetState, dateTimeState)

    val processSelection: () -> Unit = {
        BaseBehaviors.autoFinish(
            selection = selection,
            condition = dateTimeState.valid,
            coroutine = coroutine,
            onSelection = dateTimeState::onFinish,
            onFinished = sheetState::finish,
            onDisableInput = dateTimeState::disableInput
        )
    }

    FrameBase(
        header = header,
        config = config,
        content = {
            val datePicker = @Composable {
                PickerComponent(
                    config = config,
                    locale = selection.locale,
                    formatStyle = selection.dateFormatStyle!!,
                    onDateValueChange = { date ->
                        dateTimeState.processSelection(date)
                        processSelection()
                    }
                )
            }

            val timePicker = @Composable {
                PickerComponent(
                    config = config,
                    locale = selection.locale,
                    formatStyle = selection.timeFormatStyle!!,
                    onTimeValueChange = { time ->
                        dateTimeState.processSelection(time)
                        processSelection()
                    }
                )
            }

            when (selection) {
                is DateTimeSelection.Date -> datePicker()
                is DateTimeSelection.Time -> timePicker()
                is DateTimeSelection.DateTime -> {
                    if (selection.startWithTime) {
                        if (dateTimeState.timeSelection == null) timePicker()
                        if (dateTimeState.timeSelection != null) datePicker()
                    } else {
                        if (dateTimeState.dateSelection == null) datePicker()
                        if (dateTimeState.dateSelection != null) timePicker()
                    }
                }
            }
        },
        buttonsVisible = selection.withButtonView
    ) {
        ButtonsComponent(
            onPositiveValid = dateTimeState.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = dateTimeState::onFinish,
            onClose = sheetState::finish
        )
    }
}

