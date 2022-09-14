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

package com.maxkeppeler.sheets.color

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import com.maxkeppeler.sheets.color.views.ColorCustomComponent
import com.maxkeppeler.sheets.color.views.ColorSelectionModeComponent
import com.maxkeppeler.sheets.color.views.ColorTemplateComponent

/**
 * Color view for the use-case to to select a color.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun ColorView(
    selection: ColorSelection,
    config: ColorConfig = ColorConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val context = LocalContext.current
    val state = rememberSaveable(
        saver = ColorState.Saver(context, selection, config),
        init = { ColorState(context, selection, config) }
    )

    val coroutine = rememberCoroutineScope()
    val onSelection: (Int) -> Unit = {
        state.processSelection(it)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = state::onFinish,
            onFinished = onCancel,
            onDisableInput = state::disableInput
        )
    }

    FrameBase(
        header = header,
        content = {
            ColorSelectionModeComponent(
                config = config,
                selection = selection,
                mode = state.displayMode,
                onModeChange = { state.displayMode = it },
                onNoColorClick = {
                    selection.onSelectNone?.invoke()
                    onCancel()
                }
            )
            when (state.displayMode) {
                ColorSelectionMode.TEMPLATE ->
                    ColorTemplateComponent(
                        colors = state.colors,
                        selectedColor = state.color,
                        inputDisabled = state.inputDisabled,
                        onColorClick = onSelection
                    )
                ColorSelectionMode.CUSTOM ->
                    ColorCustomComponent(
                        config = config,
                        color = state.color ?: Color.Gray.toArgb(),
                        onColorChange = state::processSelection,
                    )
            }
        },
        buttonsVisible = selection.withButtonView
                || !selection.withButtonView
                && config.displayMode != ColorSelectionMode.TEMPLATE
    ) {
        ButtonsComponent(
            selection = selection,
            onPositiveValid = state.valid,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = state::onFinish,
            onCancel = onCancel
        )
    }
}

