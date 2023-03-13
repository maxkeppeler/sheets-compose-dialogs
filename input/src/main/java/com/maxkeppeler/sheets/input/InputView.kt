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

package com.maxkeppeler.sheets.input

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.input.models.InputConfig
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.views.InputComponent

/**
 * Info view for the use-case to display simple information.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun InputView(
    useCaseState: UseCaseState,
    selection: InputSelection,
    config: InputConfig = InputConfig(),
    header: Header? = null,
) {
    val inputState = rememberInputState(selection)
    StateHandler(useCaseState, inputState)

    FrameBase(
        header = header,
        config = config,
        layout = {
            InputComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                input = inputState.input,
                onInputUpdated = inputState::updateInput,
                config = config,
            )
        },
        buttonsVisible = selection.withButtonView
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            selection = selection,
            onPositiveValid = inputState.valid,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = inputState::onFinish,
            onClose = useCaseState::finish,
        )
    }
}