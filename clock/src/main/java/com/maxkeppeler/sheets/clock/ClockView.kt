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

package com.maxkeppeler.sheets.clock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.views.KeyboardComponent
import com.maxkeppeler.sheets.clock.views.TimeValueComponent

/**
 * Clock view for the use-case to to select a clock time.
 * @param sheetState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun ClockView(
    sheetState: SheetState,
    selection: ClockSelection,
    config: ClockConfig = ClockConfig(),
    header: Header? = null,
) {
    val context = LocalContext.current
    val clockState = rememberClockState(context, selection, config)
    StateHandler(sheetState, clockState)

    FrameBase(
        header = header,
        content = {
            TimeValueComponent(
                unitValues = clockState.timeTextValues,
                isAm = clockState.isAm,
                is24hourFormat = clockState.is24HourFormat,
                valueIndex = clockState.valueIndex.value,
                groupIndex = clockState.groupIndex.value,
                onGroupClick = clockState::onValueGroupClick,
                onAm = clockState::onChange12HourFormatValue,
            )
            KeyboardComponent(
                config = config,
                keys = clockState.keys,
                disabledKeys = clockState.disabledKeys,
                onEnterValue = clockState::onEnterValue,
                onPrevAction = clockState::onPrevAction,
                onNextAction = clockState::onNextAction
            )
        }
    ) {
        ButtonsComponent(
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = clockState::onFinish,
            onClose = sheetState::finish
        )
    }
}

