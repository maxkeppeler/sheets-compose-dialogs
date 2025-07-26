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
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import sheets_compose_dialogs.color.generated.resources.Res
import sheets_compose_dialogs.color.generated.resources.scd_color_dialog_custom_color
import sheets_compose_dialogs.color.generated.resources.scd_color_dialog_no_color
import sheets_compose_dialogs.color.generated.resources.scd_color_dialog_template_colors

/**
 * The color selection mode component that allows the user to switch between template colors, custom color and no color.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param mode The current color selection mode.
 * @param onModeChange The listener that returns the new color selection mode.
 * @param onNoColorClick The listener that is invoked when no color is selected.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColorSelectionModeComponent(
    selection: ColorSelection,
    config: ColorConfig,
    mode: ColorSelectionMode,
    onModeChange: (ColorSelectionMode) -> Unit,
    onNoColorClick: () -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
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
                    vertical = 8.dp,
                    horizontal = 8.dp
                ),
                shape = RoundedCornerShape(50)
            ) {
                val text = stringResource(
                    when (mode) {
                        ColorSelectionMode.CUSTOM -> Res.string.scd_color_dialog_template_colors
                        ColorSelectionMode.TEMPLATE -> Res.string.scd_color_dialog_custom_color
                    }
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = if (mode != ColorSelectionMode.TEMPLATE) config.icons.Apps else config.icons.Tune,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = text,
                )
            }
        }

        if (selection.onSelectNone != null) {
            TextButton(
                onClick = onNoColorClick,
                modifier = Modifier,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                ),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = config.icons.NotInterested,
                    contentDescription = stringResource(Res.string.scd_color_dialog_no_color),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(Res.string.scd_color_dialog_no_color),
                )
            }
        }
    }
}
