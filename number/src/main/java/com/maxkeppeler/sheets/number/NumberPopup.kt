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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.PopupProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.views.base.PopupBase
import com.maxkeppeler.sheets.number.models.NumberConfig
import com.maxkeppeler.sheets.number.models.NumberSelection

/**
 * Create a number popup that can be used to select a number.
 * @param state The state of the sheet.
 * @param selection The selection configuration for the popup.
 * @param config The general configuration for the popup.
 * @param header The header to be displayed at the top of the popup.
 * @param alignment The alignment of the popup.
 * @param offset The offset of the popup.
 * @param properties PopupProperties for further customization of this popup's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun NumberPopup(
    state: UseCaseState,
    selection: NumberSelection,
    config: NumberConfig = NumberConfig(),
    header: Header? = null,
    alignment: Alignment = Alignment.TopStart,
    offset: IntOffset = IntOffset(0, 0),
    properties: PopupProperties = PopupProperties(),
) {

    PopupBase(
        state = state,
        properties = properties,
        alignment = alignment,
        offset = offset
    ) {
        NumberView(
            useCaseState = state,
            selection = selection,
            config = config,
            header = header,
        )
    }
}
