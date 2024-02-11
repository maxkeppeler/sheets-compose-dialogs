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
package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * A replication of LazyVerticalGrid - but not lazy.
 * @param modifier The modifier that is applied to this component.
 * @param items The items that will be displayed in the grid.
 * @param columns The amount of columns the grid should have.
 * @param rowSpacing The spacing between rows.
 * @param columnSpacing The spacing between columns.
 * @param itemView The view that will be drawn into a cell and represents the item.
 */
@Composable
fun <T> Grid(
    modifier: Modifier,
    items: List<T>,
    columns: Int,
    rowSpacing: Dp,
    columnSpacing: Dp,
    itemView: @Composable (T) -> Unit,
) {
    val rows = items.chunked(columns)
    val extraSpacings = rows.size.minus(1).times(rowSpacing.value)

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val itemHeight = Dp(maxHeight.value.minus(extraSpacings).div(rows.size))
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(rowSpacing)
        ) {
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.heightIn(max = itemHeight),
                    horizontalArrangement = Arrangement.spacedBy(columnSpacing),
                ) {
                    rowItems.forEach { item ->
                        Column(
                            modifier = Modifier.weight(1f, true),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = { itemView(item) }
                        )
                    }
                    // Add a filler column if the row has less columns than the total number of columns
                    if (rowItems.size < columns) {
                        repeat(columns - rowItems.size) {
                            Column(
                                modifier = Modifier.weight(1f, true),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {}
                        }
                    }
                }
            }
        }
    }
}