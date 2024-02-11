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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.date_time

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.date_time.views.PickerComponent

/**
 * Date Time dialog for the use-case to select a date, time or both in a quick way.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun DateTimeView(
    useCaseState: UseCaseState,
    selection: DateTimeSelection,
    config: DateTimeConfig = DateTimeConfig(),
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()
    val state = rememberDateTimeState(selection, config)
    StateHandler(useCaseState, state)

    val processSelection: () -> Unit = {
        BaseBehaviors.autoFinish(
            selection = selection,
            condition = state.valid,
            coroutine = coroutine,
            onSelection = state::onFinish,
            onFinished = useCaseState::finish,
            onDisableInput = state::disableInput
        )
    }

    LaunchedEffect(state.isDateValid) { processSelection() }
    LaunchedEffect(state.isDateValid) { processSelection() }

    val skipButton: @Composable ColumnScope.(Int, Boolean) -> Unit = @Composable { labelRes, error ->
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = state::skipSelection,
            colors = ButtonDefaults.buttonColors(
                containerColor = if(error) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
                contentColor = if(error) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(stringResource(labelRes))
        }
    }
    val datePicker = @Composable {
        PickerComponent(
            isDate = true,
            values = state.dateValues!!,
            config = config,
            onValueChange = state::updateValue,
        )
    }

    val timePicker = @Composable {
        PickerComponent(
            isDate = false,
            values = state.timeValues!!,
            config = config,
            onValueChange = state::updateValue,
        )
    }

    FrameBase(
        header = header,
        config = config,
        layout = {
            Column(modifier = Modifier.animateContentSize()) {
                when (selection) {
                    is DateTimeSelection.Date -> datePicker()
                    is DateTimeSelection.Time -> timePicker()
                    is DateTimeSelection.DateTime -> {
                        if (selection.startWithTime) {
                            if (!state.firstSkipped) {
                                timePicker()
                                skipButton(R.string.sheets_compose_dialogs_set_date, !state.isDateValid)
                            } else {
                                datePicker()
                                skipButton(R.string.sheets_compose_dialogs_set_time, !state.isTimeValid)
                            }
                        } else {
                            if (!state.firstSkipped) {
                                datePicker()
                                skipButton(R.string.sheets_compose_dialogs_set_time, !state.isTimeValid)
                            } else {
                                timePicker()
                                skipButton(R.string.sheets_compose_dialogs_set_date, !state.isDateValid)
                            }
                        }
                    }
                }
            }
        },
        buttonsVisible = selection.withButtonView
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            onPositiveValid = state.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = state::onFinish,
            state = useCaseState,
        )
    }
}

