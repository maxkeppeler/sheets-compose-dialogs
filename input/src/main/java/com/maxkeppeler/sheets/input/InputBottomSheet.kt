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
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.views.base.BottomSheetBase
import com.maxkeppeler.sheets.input.models.InputConfig
import com.maxkeppeler.sheets.input.models.InputSelection

/**
 * Input bottom sheet for the use-case to display simple information.
 * @param state The state of the sheet.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 * @param allowDismiss Allow the bottom sheet to be dismissed.
 */
@ExperimentalMaterial3Api
@Composable
fun InputBottomSheet(
    state: UseCaseState,
    selection: InputSelection,
    config: InputConfig = InputConfig(),
    header: Header? = null,
    allowDismiss: Boolean = true,
) {

    BottomSheetBase(
        state = state,
        allowDismiss = allowDismiss,
    ) {
        InputView(
            useCaseState = state,
            selection = selection,
            config = config,
            header = header,
        )
    }
}