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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.color.models.ColorConfig
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import sheets_compose_dialogs.color.generated.resources.Res
import sheets_compose_dialogs.color.generated.resources.scd_color_dialog_transparent_pattern

/**
 * The template item component that represents one color.
 * @param config The general configuration for the dialog view.
 * @param modifier The modifier that is applied to this component.
 * @param color The color that this item represents.
 * @param selected If the color was selected.
 * @param inputDisabled If input is disabled.
 * @param onColorClick The listener that returns the selected color.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColorTemplateItemComponent(
    config: ColorConfig,
    modifier: Modifier = Modifier,
    color: Int,
    selected: Boolean,
    inputDisabled: Boolean = false,
    onColorClick: (Int) -> Unit
) {
    val colorShape = MaterialTheme.shapes.medium
    Box(modifier = modifier.aspectRatio(1f)) {
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Center)
                .aspectRatio(1f),
            shape = colorShape,
            onClick = { onColorClick(color) },
            enabled = !inputDisabled
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(Res.drawable.scd_color_dialog_transparent_pattern),
                    contentDescription = null,
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(color))
                ) {
                }
            }
        }
        if (selected) {
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    imageVector = config.icons.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else if (Color(color).alpha < 1F) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(2.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall,
                text = "${Color(color).alpha.times(100).toInt()}%"
            )
        }
    }
}