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

package com.maxkeppeler.sheets.number

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.number.models.NumberConfig
import com.maxkeppeler.sheets.number.models.NumberSelection
import com.maxkeppeler.sheets.number.views.KeyboardComponent
import com.maxkeppeler.sheets.number.views.LandscapeNumberValueComponent
import com.maxkeppeler.sheets.number.views.NumberHintComponent
import com.maxkeppeler.sheets.number.views.PortraitNumberValueComponent

/**
 * Number view for the use-case to to select a number.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun NumberView(
    useCaseState: UseCaseState,
    selection: NumberSelection,
    config: NumberConfig = NumberConfig(),
    header: Header? = null,
) {
    val context = LocalContext.current
    val numberState = rememberNumberState(context, selection, config)
    StateHandler(useCaseState, numberState)

    FrameBase(
        header = header,
        config = config,
        layoutHorizontalAlignment = Alignment.CenterHorizontally,
        layout = {
            PortraitNumberValueComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.scd_normal_100)),
                unitValues = numberState.timeTextValues,
                valueIndex = numberState.valueIndex.intValue,
                groupIndex = numberState.groupIndex.intValue,
                onGroupClick = numberState::onValueGroupClick,
            )
            NumberHintComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.scd_normal_150)),
                valid = numberState.valid,
            )
            KeyboardComponent(
                modifier = Modifier
                    .sizeIn(maxHeight = BaseConstants.KEYBOARD_HEIGHT_MAX)
                    .aspectRatio(BaseConstants.KEYBOARD_RATIO),
                orientation = LibOrientation.PORTRAIT,
                config = config,
                keys = numberState.keys,
                onEnterValue = numberState::onEnterValue,
                onPrevAction = numberState::onPrevAction,
                onNextAction = numberState::onNextAction
            )
        },
        layoutLandscape = {
            Column(
                Modifier
                    .weight(1f)
                    .weight(1f, true)
            ) {
                LandscapeNumberValueComponent(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    unitValues = numberState.timeTextValues,
                    valueIndex = numberState.valueIndex.intValue,
                    groupIndex = numberState.groupIndex.intValue,
                    onGroupClick = numberState::onValueGroupClick,
                )
                NumberHintComponent(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    valid = numberState.valid,
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
                keys = numberState.keys,
                onEnterValue = numberState::onEnterValue,
                onPrevAction = numberState::onPrevAction,
                onNextAction = numberState::onNextAction
            )
        }
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = numberState::onFinish,
            onPositiveValid = numberState.valid,
            state = useCaseState,
        )
    }
}

