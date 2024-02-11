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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.*

@Composable
internal fun InputSample1(closeSelection: () -> Unit) {

    val inputOptions = listOf(
        InputText("Thanks for taking part on this short survey! :)"),
        InputDivider(),
        InputRadioButtonGroup(
            header = InputHeader(
                title = "Where did you read about sheets?",
                body = "It helps us determine where to be more active.",
                icon = IconSource(Icons.Filled.Language)
            ),
            items = listOf("GitHub", "Twitter", "Other"),
            required = true,
            key = "Source"
        ),
    )

    InputDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                val selectionIndex = result.getInt("Source") // or index 2 if no key was set
                // Handle selection
            },
        )
    )
}