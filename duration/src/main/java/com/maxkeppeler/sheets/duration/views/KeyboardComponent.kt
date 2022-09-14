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
package com.maxkeppeler.sheets.duration.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.duration.utils.Constants
import com.maxkeppeler.sheets.core.R as RC

/**
 * The keyboard component that is used to input the clock time.
 * @param keys A list of keys that will be displayed.
 * @param onEnterValue The listener that is invoked when a value was clicked.
 * @param onBackspaceAction The listener that is invoked when [Constants.ACTION_BACKSPACE] was clicked.
 * @param onEmptyAction The listener that is invoked when [Constants.ACTION_CLEAR] was clicked.
 */
@Composable
internal fun KeyboardComponent(
    keys: List<String>,
    onEnterValue: (String) -> Unit,
    onBackspaceAction: () -> Unit,
    onEmptyAction: () -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(Constants.KEYBOARD_COLUMNS),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100))
    ) {
        items(keys) { option ->
            KeyItemComponent(
                key = option,
                onBackspaceAction = onBackspaceAction,
                onEmptyAction = onEmptyAction,
                onEnterValue = onEnterValue
            )
        }
    }
}
