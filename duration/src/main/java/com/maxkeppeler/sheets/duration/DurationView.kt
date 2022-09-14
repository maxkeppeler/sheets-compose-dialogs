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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.duration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.views.KeyboardComponent
import com.maxkeppeler.sheets.duration.views.TimeDisplayComponent
import com.maxkeppeler.sheets.core.R as RC

/**
 * Duration view for the use-case to to select a duration time.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun DurationView(
    selection: DurationSelection,
    config: DurationConfig = DurationConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {

    val state = rememberSaveable(
        saver = DurationState.Saver(selection, config),
        init = { DurationState(selection, config) }
    )

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(
            top = dimensionResource(RC.dimen.scd_normal_150)
        ),
        content = {
            TimeDisplayComponent(
                indexOfFirstValue = state.indexOfFirstValue,
                valuePairs = state.valuePairs,
                minTimeValue = state.timeInfoInSeconds.second,
                maxTimeValue = state.timeInfoInSeconds.third
            )
            KeyboardComponent(
                keys = state.keys,
                onEnterValue = state::onEnterValue,
                onBackspaceAction = state::onBackspaceAction,
                onEmptyAction = state::onEmptyAction
            )
        },
        buttons = {
            ButtonsComponent(
                onPositiveValid = state.valid,
                selection = selection,
                onNegative = { selection.onNegativeClick?.invoke() },
                onPositive = state::onFinish,
                onCancel = onCancel
            )
        }
    )
}
