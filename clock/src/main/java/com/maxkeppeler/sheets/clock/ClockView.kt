/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.views.KeyboardComponent
import com.maxkeppeler.sheets.clock.views.LandscapeTimeValueComponent
import com.maxkeppeler.sheets.clock.views.PortraitTimeValueComponent
import com.maxkeppeler.sheets.clock.views.TimeHintComponent
import com.maxkeppeler.sheets.core.R

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
        config = config,
        layoutHorizontalAlignment = Alignment.CenterHorizontally,
        layout = {
            PortraitTimeValueComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.scd_normal_100)),
                unitValues = clockState.timeTextValues,
                isAm = clockState.isAm,
                is24hourFormat = clockState.is24HourFormat,
                valueIndex = clockState.valueIndex.value,
                groupIndex = clockState.groupIndex.value,
                onGroupClick = clockState::onValueGroupClick,
                onAm = clockState::onChange12HourFormatValue,
            )
            TimeHintComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.scd_normal_150)),
                valid = clockState.valid,
                boundary = config.boundary,
            )
            KeyboardComponent(
                modifier = Modifier
                    .sizeIn(maxHeight = BaseConstants.KEYBOARD_HEIGHT_MAX)
                    .aspectRatio(BaseConstants.KEYBOARD_RATIO),
                orientation = LibOrientation.PORTRAIT,
                config = config,
                keys = clockState.keys,
                disabledKeys = clockState.disabledKeys,
                onEnterValue = clockState::onEnterValue,
                onPrevAction = clockState::onPrevAction,
                onNextAction = clockState::onNextAction
            )
        },
        layoutLandscape = {
            Column(
                Modifier
                    .weight(1f)
                    .weight(1f, true)
            ) {
                LandscapeTimeValueComponent(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    unitValues = clockState.timeTextValues,
                    isAm = clockState.isAm,
                    is24hourFormat = clockState.is24HourFormat,
                    valueIndex = clockState.valueIndex.value,
                    groupIndex = clockState.groupIndex.value,
                    onGroupClick = clockState::onValueGroupClick,
                    onAm = clockState::onChange12HourFormatValue,
                )
                TimeHintComponent(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    valid = clockState.valid,
                    boundary = config.boundary,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            KeyboardComponent(
                modifier = Modifier
                    .weight(1f, true)
                    .sizeIn(maxHeight = BaseConstants.KEYBOARD_HEIGHT_MAX)
                    .aspectRatio(BaseConstants.KEYBOARD_RATIO),
                orientation = LibOrientation.LANDSCAPE,
                config = config,
                keys = clockState.keys,
                disabledKeys = clockState.disabledKeys,
                onEnterValue = clockState::onEnterValue,
                onPrevAction = clockState::onPrevAction,
                onNextAction = clockState::onNextAction
            )
        }
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = clockState::onFinish,
            onPositiveValid = clockState.valid,
            onClose = sheetState::finish
        )
    }
}

