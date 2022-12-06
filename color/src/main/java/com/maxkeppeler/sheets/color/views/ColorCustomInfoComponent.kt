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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.utils.Constants
import com.maxkeppeler.sheets.color.utils.copyColorIntoClipboard
import com.maxkeppeler.sheets.color.utils.getFormattedColor
import com.maxkeppeler.sheets.color.utils.pasteColorFromClipboard
import kotlinx.coroutines.delay
import com.maxkeppeler.sheets.core.R as RC

/**
 * A information view for the custom color picker.
 * @param config The general configuration for the dialog view.
 * @param color The color that is currently selected.
 * @param onColorChange The listener that returns a selected color.
 */
@Composable
internal fun ColorCustomInfoComponent(
    config: ColorConfig,
    color: Int,
    onColorChange: (Int) -> Unit,
) {
    val context = LocalContext.current
    val colorPasteError = rememberSaveable { mutableStateOf<String?>(null) }
    val onCopyCustomColor = {
        copyColorIntoClipboard(
            ctx = context,
            label = context.getString(R.string.scd_color_dialog_color),
            value = getFormattedColor(color)
        )
    }
    val onPasteCustomColor = {
        pasteColorFromClipboard(
            ctx = context,
            onPastedColor = { onColorChange(it) },
            onPastedColorFailure = { colorPasteError.value = it },
        )
    }
    LaunchedEffect(colorPasteError.value) {
        delay(3000)
        colorPasteError.value = null
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ColorTemplateItemComponent(
            config = config,
            modifier = Modifier
                .testTags(TestTags.COLOR_CUSTOM_SELECTION, color)
                .size(Constants.COLOR_CUSTOM_ITEM_SIZE),
            color = color,
            selected = false,
            onColorClick = onColorChange
        )

        ElevatedCard(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(start = dimensionResource(RC.dimen.scd_normal_100))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                    .padding(end = dimensionResource(RC.dimen.scd_small_100)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(start = 0.dp)) {
                    if (colorPasteError.value != null) {
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            text = colorPasteError.value!!,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.scd_color_dialog_argb),
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_small_25)),
                            text = getFormattedColor(color),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1
                        )
                    }
                }
                if (colorPasteError.value == null) {
                    Spacer(modifier = Modifier.weight(1f))
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier,
                        onClick = onCopyCustomColor
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(dimensionResource(RC.dimen.scd_size_150)),
                            imageVector = config.icons.ContentCopy,
                            contentDescription = stringResource(R.string.scd_color_dialog_copy_color),
                        )
                    }
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier,
                        onClick = onPasteCustomColor
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(dimensionResource(RC.dimen.scd_size_150)),
                            imageVector = config.icons.ContentPaste,
                            contentDescription = stringResource(R.string.scd_color_dialog_paste_color),
                        )
                    }
                }
            }
        }
    }
}
