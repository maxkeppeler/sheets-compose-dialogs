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

package com.maxkeppeler.sheets.info

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.maxkeppeler.sheets.info.views.BodyComponent

/**
 * Info view for the use-case to display simple information.
 * @param selection The selection configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param body The body content to be displayed inside the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun InfoView(
    selection: InfoSelection,
    header: Header? = null,
    body: Body,
    onCancel: () -> Unit = {},
) {
    FrameBase(
        header = header,
        content = { BodyComponent(body) },
        buttonsVisible = selection.withButtonView
    ) {
        ButtonsComponent(
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = selection.onPositiveClick::invoke,
            onCancel = onCancel
        )
    }
}

