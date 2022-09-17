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
package com.maxkeppeler.sheets.input.views

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.input.models.*

/**
 * The type component that displays the respective correct input type.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputItemTypeComponent(
    input: Input,
    onInputUpdated: (Input) -> Unit
) {
    when (input) {
        is InputRadioButtonGroup -> InputRadioButtonGroupComponent(input, onInputUpdated)
        is InputCheckboxGroup -> InputCheckboxGroupComponent(input, onInputUpdated)
        is InputCheckbox -> InputCheckboxComponent(input, onInputUpdated)
        is InputTextField -> InputTextFieldComponent(input, onInputUpdated)
        is InputText -> InputTextComponent(input)
        is InputDivider -> InputDividerComponent()
        is InputView -> InputViewComponent(input)
    }
}