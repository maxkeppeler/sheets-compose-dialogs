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

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.input.models.*

/**
 * The type component that displays the respective correct input type.
 * @param index The index of the input relative to all inputs.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputItemTypeComponent(
    index: Int,
    input: Input,
    onInputUpdated: (Input) -> Unit
) {
    when (input) {
        is InputRadioButtonGroup -> InputRadioButtonGroupComponent(index, input, onInputUpdated)
        is InputCheckboxGroup -> InputCheckboxGroupComponent(index, input, onInputUpdated)
        is InputCheckbox -> InputCheckboxComponent(index, input, onInputUpdated)
        is InputTextField -> InputTextFieldComponent(index, input, onInputUpdated)
        is InputText -> InputTextComponent(index, input)
        is InputDivider -> InputDividerComponent(index)
        is InputCustomView -> InputCustomViewComponent(input)
    }
}