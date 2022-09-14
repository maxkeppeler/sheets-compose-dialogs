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


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.core.R as RC

/**
 * The item component for an option.
 * @param option The option that will be displayed.
 * @param onClick The listener that is invoked when an option was clicked.
 * @param grid Display option as a grid item.
 * @param inputDisabled If input is disabled.
 * @param size The size that should be applied to the option component.
 */
@Composable
internal fun OptionItemComponent(
    option: Option,
    inputDisabled: Boolean,
    onClick: (Option) -> Unit,
    grid: Boolean = true,
    size: MutableState<Size?>? = null,
) {
    val backgroundColor = if (option.selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceVariant

    val iconColor = if (option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val textColor = if (option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val containerModifier = Modifier
        .wrapContentHeight()
        .clip(MaterialTheme.shapes.medium)
        .clickable(!inputDisabled && !option.disabled) { onClick(option) }
        .then(if (option.disabled || option.selected) Modifier.background(backgroundColor) else Modifier)

    val showInfoDialog = rememberSaveable { mutableStateOf(false) }
    option.details?.let {
        OptionDetailsDialog(
            show = showInfoDialog,
            option = option,
            backgroundColor = backgroundColor,
            iconColor = iconColor,
            textColor = textColor,
        )
    }

    val onInfoClick = { showInfoDialog.value = !showInfoDialog.value }

    if (grid) OptionGridItemComponent(
        option = option,
        modifier = containerModifier,
        iconColor = iconColor,
        textColor = textColor,
        size = size,
        onInfoClick = onInfoClick
    ) else OptionListItemComponent(
        option = option,
        modifier = containerModifier,
        iconColor = iconColor,
        textColor = textColor,
        onInfoClick = onInfoClick
    )
}