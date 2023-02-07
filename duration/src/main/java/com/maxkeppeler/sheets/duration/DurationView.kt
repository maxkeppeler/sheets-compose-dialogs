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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.duration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.views.KeyboardComponent
import com.maxkeppeler.sheets.duration.views.TimeDisplayComponent

/**
 * Duration view for the use-case to to select a duration time.
 * @param sheetState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun DurationView(
    sheetState: SheetState,
    selection: DurationSelection,
    config: DurationConfig = DurationConfig(),
    header: Header? = null,
) {
    val durationState = rememberDurationState(selection, config)
    StateHandler(sheetState, durationState)

    FrameBase(
        header = header,
        config = config,
        layoutHorizontalAlignment = Alignment.CenterHorizontally,
        layout = {
            TimeDisplayComponent(
                modifier = Modifier,
                orientation = LibOrientation.PORTRAIT,
                indexOfFirstValue = durationState.indexOfFirstValue,
                values = durationState.values,
                minTimeValue = durationState.timeInfoInSeconds.second,
                maxTimeValue = durationState.timeInfoInSeconds.third
            )
            KeyboardComponent(
                modifier = Modifier
                    .sizeIn(maxHeight = BaseConstants.KEYBOARD_HEIGHT_MAX)
                    .aspectRatio(BaseConstants.KEYBOARD_RATIO),
                orientation = LibOrientation.PORTRAIT,
                config = config,
                keys = durationState.keys,
                onEnterValue = durationState::onEnterValue,
                onBackspaceAction = durationState::onBackspaceAction,
                onEmptyAction = durationState::onEmptyAction
            )
        },
        layoutLandscape = {
            TimeDisplayComponent(
                modifier = Modifier.weight(1f, true),
                orientation = LibOrientation.LANDSCAPE,
                indexOfFirstValue = durationState.indexOfFirstValue,
                values = durationState.values,
                minTimeValue = durationState.timeInfoInSeconds.second,
                maxTimeValue = durationState.timeInfoInSeconds.third
            )
            Spacer(modifier = Modifier.width(16.dp))
            KeyboardComponent(
                modifier = Modifier
                    .weight(1f, true)
                    .sizeIn(maxHeight = BaseConstants.KEYBOARD_HEIGHT_MAX)
                    .aspectRatio(BaseConstants.KEYBOARD_RATIO),
                orientation = LibOrientation.LANDSCAPE,
                config = config,
                keys = durationState.keys,
                onEnterValue = durationState::onEnterValue,
                onBackspaceAction = durationState::onBackspaceAction,
                onEmptyAction = durationState::onEmptyAction
            )
        }
    ) {
        ButtonsComponent(
            onPositiveValid = durationState.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = durationState::onFinish,
            onClose = sheetState::finish,
        )
    }
}
