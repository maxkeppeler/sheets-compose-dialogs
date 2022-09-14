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
package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view for the selection of the options.
 * @param modifier The modifier that is applied to this component.
 * @param config The general configuration.
 * @param options The list of options.
 * @param inputDisabled If input is disabled.
 * @param onOptionChange Listener that is invoked when the state of an option changes.
 */
@Composable
fun OptionComponent(
    modifier: Modifier,
    config: OptionConfig,
    options: List<Option>,
    inputDisabled: Boolean,
    onOptionChange: (Option) -> Unit,
) {

    val verticalDirectionModifier = modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(RC.dimen.scd_normal_150))

    val columnsLimits = config.gridColumns
    val columns = if (options.size < columnsLimits) options.size else columnsLimits

    val onClick: (Option) -> Unit = { option ->
        val newOption = option.copy(selected = !option.selected).apply {
            position = option.position
        }
        onOptionChange(newOption)
    }

    val size = remember { mutableStateOf<Size?>(null) }

    when (config.mode) {
        DisplayMode.GRID_HORIZONTAL -> {
            LazyRow(
                contentPadding = PaddingValues(horizontal = dimensionResource(RC.dimen.scd_normal_150)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100))
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        inputDisabled = inputDisabled,
                        onClick = onClick,
                        size = size,
                    )
                }
            }
        }
        DisplayMode.GRID_VERTICAL -> {
            LazyVerticalGrid(
                modifier = verticalDirectionModifier,
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100))
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        inputDisabled = inputDisabled,
                        onClick = onClick,
                        size = size,
                    )
                }
            }
        }
        DisplayMode.LIST -> {
            LazyColumn(
                modifier = verticalDirectionModifier,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_100))
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        inputDisabled = inputDisabled,
                        onClick = onClick,
                        grid = false
                    )
                }
            }
        }
    }
}