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
package com.maxkeppeler.sheets.input.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.input.models.Input
import com.maxkeppeler.sheets.input.models.InputConfig

/**
 * The parent container for the individual input item type components.
 * It handles the additional header and the required-overlay.
 * @param config The general configuration for the dialog view.
 * @param index The index of the input.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputItemComponent(
    config: InputConfig,
    index: Int,
    input: Input,
    onInputUpdated: (Input) -> Unit
) {
    Box {
        Column {
            input.header?.let { InputItemHeaderComponent(index, it) }
            InputItemTypeComponent(index, input, onInputUpdated)
        }
        if (input.required && !input.valid) {
            InputItemOverlayComponent(config, index)
        }
    }
}