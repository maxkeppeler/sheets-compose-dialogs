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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import com.maxkeppeler.sheets.option.views.OptionBoundsComponent
import com.maxkeppeler.sheets.option.views.OptionComponent

/**
 * Option view for the use-case to display a list or grid of options.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun OptionView(
    useCaseState: UseCaseState,
    selection: OptionSelection,
    config: OptionConfig = OptionConfig(),
    header: Header? = null,
) {

    val coroutine = rememberCoroutineScope()
    val optionState = rememberOptionState(selection, config)
    StateHandler(useCaseState, optionState)

    val processSelection: (Option) -> Unit = { option ->
        optionState.processSelection(option)
        BaseBehaviors.autoFinish(
            selection = selection,
            condition = optionState.valid,
            coroutine = coroutine,
            onSelection = optionState::onFinish,
            onFinished = useCaseState::finish,
            onDisableInput = optionState::disableInput
        )
    }

    FrameBase(
        header = header,
        config = config,
        // Override content padding, spacing is within the scrollable container for display mode GRID_HORIZONTAL
        horizontalContentPadding = PaddingValues(horizontal = 0.dp),
        layout = {
            OptionBoundsComponent(
                selection = selection,
                selectedOptions = optionState.selectedOptions
            )
            OptionComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                config = config,
                options = optionState.options,
                inputDisabled = optionState.inputDisabled,
                onOptionChange = processSelection
            )
        },
        buttonsVisible = selection.withButtonView
    ) {orientation ->
        ButtonsComponent(
            orientation = orientation,
            onPositiveValid = optionState.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = optionState::onFinish,
            state = useCaseState,
        )
    }
}





