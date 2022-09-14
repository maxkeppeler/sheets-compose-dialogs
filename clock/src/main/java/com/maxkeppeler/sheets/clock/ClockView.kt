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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.views.KeyboardComponent
import com.maxkeppeler.sheets.clock.views.TimeValueComponent
import com.maxkeppeler.sheets.core.R as RC

/**
 * Clock view for the use-case to to select a clock time.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun ClockView(
    selection: ClockSelection,
    config: ClockConfig = ClockConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val context = LocalContext.current
    val state = rememberSaveable(
        saver = ClockState.Saver(context, selection, config),
        init = { ClockState(context, selection, config) }
    )

    FrameBase(
        header = header,
        content = {
            TimeValueComponent(
                unitValues = state.timeTextValues,
                isAm = state.isAm,
                is24hourFormat = state.is24HourFormat,
                valueIndex = state.valueIndex.value,
                groupIndex = state.groupIndex.value,
                onGroupClick = state::onValueGroupClick,
                onAm = state::onChange12HourFormatValue,
            )
            KeyboardComponent(
                keys = state.keys,
                disabledKeys = state.disabledKeys,
                onEnterValue = state::onEnterValue,
                onPrevAction = state::onPrevAction,
                onNextAction = state::onNextAction
            )
        }
    ) {
        ButtonsComponent(
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = state::onFinish,
            onCancel = onCancel
        )
    }
}

