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
package com.maxkeppeler.sheets.clock.views


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.views.Grid
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.utils.Constants

/**
 * The keyboard component that is used to input the clock time.
 * @param config The general configuration for the dialog view.
 * @param keys A list of keys that will be displayed.
 * @param disabledKeys A list of the keys that are displayed.
 * @param onEnterValue The listener that is invoked when a value was clicked.
 * @param onPrevAction The listener that is invoked when [Constants.ACTION_PREV] was clicked.
 * @param onNextAction The listener that is invoked when [Constants.ACTION_NEXT] was clicked.
 */
@Composable
internal fun KeyboardComponent(
    modifier: Modifier,
    config: ClockConfig,
    keys: List<String>,
    orientation: Orientation = Orientation.Vertical,
    disabledKeys: List<String>,
    onEnterValue: (Int) -> Unit,
    onPrevAction: () -> Unit,
    onNextAction: () -> Unit
) {
    Grid(
        modifier = modifier,
        items = keys,
        columns = Constants.KEYBOARD_COLUMNS,
        rowSpacing = 8.dp,
        columnSpacing = 8.dp,
        itemView = { key ->
            val disabled = disabledKeys.contains(key)
            KeyItemComponent(
                config = config,
                key = key,
                orientation = orientation,
                disabled = disabled,
                onNextAction = onNextAction,
                onPrevAction = onPrevAction,
                onEnterValue = onEnterValue
            )
        }
    )
}
