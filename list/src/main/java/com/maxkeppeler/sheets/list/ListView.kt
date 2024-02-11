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
@file:Suppress("UNUSED_PARAMETER")

package com.maxkeppeler.sheets.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.list.views.ListOptionBoundsComponent
import com.maxkeppeler.sheets.list.views.ListOptionComponent

/**
 * List view for the use-case to display a list of options.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun ListView(
    useCaseState: UseCaseState,
    selection: ListSelection,
    config: ListConfig = ListConfig(),
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()
    val listState = rememberListState(selection, config)
    StateHandler(useCaseState, listState)

    val processSelection: (ListOption) -> Unit = { option ->
        listState.processSelection(option)
        BaseBehaviors.autoFinish(
            selection = selection,
            condition = listState.valid,
            coroutine = coroutine,
            onSelection = listState::onFinish,
            onFinished = useCaseState::finish,
            onDisableInput = listState::disableInput
        )
    }

    FrameBase(
        header = header,
        config = config,
        layout = {
            ListOptionBoundsComponent(
                selection = selection,
                selectedOptions = listState.selectedOptions
            )
            ListOptionComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                selection = selection,
                options = listState.options,
                inputDisabled = listState.inputDisabled,
                onOptionChange = processSelection
            )
        },
        buttonsVisible = selection.withButtonView
    ) {orientation ->
        ButtonsComponent(
            orientation = orientation,
            onPositiveValid = listState.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = listState::onFinish,
            state = useCaseState,
        )
    }
}

