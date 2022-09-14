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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase

/**
 * Core view that functions as the base of a custom use-case.
 * @param selection The selection configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param body The body content to be displayed inside the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 * @param onPositiveValid If the positive button is valid and therefore enabled.
 */
@ExperimentalMaterial3Api
@Composable
fun CoreView(
    selection: CoreSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onCancel: () -> Unit,
    onPositiveValid: Boolean = true
) {

    FrameBase(
        header = header,
        content = { body() },
        buttonsVisible = selection.withButtonView
    ) {
        ButtonsComponent(
            onPositiveValid = onPositiveValid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = { selection.onPositiveClick?.invoke() },
            onCancel = onCancel
        )
    }
}

