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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputCheckbox
import com.maxkeppeler.sheets.input.models.InputConfig
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.models.InputText

@Composable
internal fun InputSample3(closeSelection: () -> Unit) {

    val inputOptions = listOf(
        InputText(text = "In which countries have you already been?"),
        InputCheckbox(text = "Germany", columns = 1),
        InputCheckbox(text = "Thailand", columns = 1),
        InputCheckbox(text = "Philippines", columns = 1),
        InputCheckbox(text = "China", columns = 1),
    )

    InputDialog(
        state = rememberSheetState(onCloseRequest = { closeSelection() }),
        config = InputConfig(columns = 2),
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                val selectionIndex = result.getIntArray("Fruits") // or index 0 if no key was set
                // Handle selection
            },
        )
    )
}