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
package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import com.maxkeppeler.sheets.core.R as RC

/**
 * The color selection mode component that allows the user to switch between template colors, custom color and no color.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param mode The current color selection mode.
 * @param onModeChange The listener that returns the new color selection mode.
 * @param onNoColorClick The listener that is invoked when no color is selected.
 */
@Composable
internal fun ColorSelectionModeComponent(
    selection: ColorSelection,
    config: ColorConfig,
    mode: ColorSelectionMode,
    onModeChange: (ColorSelectionMode) -> Unit,
    onNoColorClick: () -> Unit
) {
    Row(modifier = Modifier.padding(bottom = dimensionResource(RC.dimen.scd_small_100))) {
        if (config.displayMode == null) {

            TextButton(
                onClick = {
                    onModeChange(
                        if (mode == ColorSelectionMode.TEMPLATE) ColorSelectionMode.CUSTOM
                        else ColorSelectionMode.TEMPLATE
                    )
                },
                modifier = Modifier,
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = RC.dimen.scd_small_100),
                    horizontal = dimensionResource(id = RC.dimen.scd_small_100)
                ),
                shape = RoundedCornerShape(50)
            ) {
                val text = stringResource(
                    when (mode) {
                        ColorSelectionMode.CUSTOM -> R.string.scd_color_dialog_template_colors
                        ColorSelectionMode.TEMPLATE -> R.string.scd_color_dialog_custom_color
                    }
                )
                Icon(
                    modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                    imageVector = if (mode != ColorSelectionMode.TEMPLATE) config.icons.Apps else config.icons.Tune,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = dimensionResource(RC.dimen.scd_small_100)),
                    text = text,
                )
            }
        }

        if (selection.onSelectNone != null) {
            TextButton(
                onClick = onNoColorClick,
                modifier = Modifier,
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = RC.dimen.scd_small_100),
                    horizontal = dimensionResource(id = RC.dimen.scd_small_100)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                    imageVector = config.icons.NotInterested,
                    contentDescription = stringResource(R.string.scd_color_dialog_no_color),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = dimensionResource(RC.dimen.scd_small_100)),
                    text = stringResource(R.string.scd_color_dialog_no_color),
                )
            }
        }
    }
}
