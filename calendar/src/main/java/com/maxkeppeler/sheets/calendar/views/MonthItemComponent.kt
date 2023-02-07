/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.calendar.utils.Constants
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import com.maxkeppeler.sheets.core.R as RC

/**
 * The item component of the month selection view.
 * @param month The month that this item represents.
 * @param thisMonth The current month.
 * @param selected If the month is selected.
 * @param onMonthClick The listener that is invoked when a year is selected.
 */
@Composable
internal fun MonthItemComponent(
    month: Month,
    thisMonth: Boolean = false,
    disabled: Boolean = false,
    selected: Boolean = false,
    onMonthClick: () -> Unit
) {
    val textStyle =
        when {
            selected -> MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onPrimary)
            thisMonth -> MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.primary)
            else -> MaterialTheme.typography.bodyMedium
        }

    val baseModifier = Modifier
        .wrapContentWidth()
        .padding(dimensionResource(RC.dimen.scd_small_50))
        .clickable(!disabled) { onMonthClick() }

    val normalModifier = baseModifier
        .clip(MaterialTheme.shapes.small)

    val selectedModifier = normalModifier
        .background(MaterialTheme.colorScheme.primary)

    val textAlpha = when {
        disabled -> Constants.DATE_ITEM_DISABLED_TIMELINE_OPACITY
        else -> Constants.DATE_ITEM_OPACITY
    }

    Column(
        modifier = when {
            disabled -> normalModifier
            selected -> selectedModifier
            thisMonth -> baseModifier
            else -> normalModifier
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .alpha(textAlpha)
                .padding(horizontal = dimensionResource(RC.dimen.scd_small_150))
                .padding(vertical = dimensionResource(RC.dimen.scd_small_100)),
            text = LocalDate.now().withMonth(month.value)
                .format(DateTimeFormatter.ofPattern("MMM")),
            style = textStyle,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}