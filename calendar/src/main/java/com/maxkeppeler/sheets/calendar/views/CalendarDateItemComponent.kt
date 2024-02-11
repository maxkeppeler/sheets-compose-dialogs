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
package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.calendar.models.CalendarDateData
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.utils.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.maxkeppeler.sheets.core.R as RC

/**
 * The date item component of the calendar view.
 * @param data The data for the date.
 * @param selection The selection configuration for the dialog view.
 * @param onDateClick The listener that is invoked when a date is clicked.
 */
@Composable
internal fun CalendarDateItemComponent(
    orientation: LibOrientation,
    data: CalendarDateData,
    selection: CalendarSelection,
    onDateClick: (LocalDate) -> Unit = {},
) {

    val today = data.date == LocalDate.now()
    val defaultShape = when (orientation) {
        LibOrientation.PORTRAIT -> MaterialTheme.shapes.medium
        LibOrientation.LANDSCAPE -> MaterialTheme.shapes.small
    }

    val shape = when {
        data.selectedStart -> defaultShape.copy(
            topEnd = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp)
        )

        data.selectedEnd -> defaultShape.copy(
            topStart = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        )

        data.selectedBetween -> RoundedCornerShape(0)
        else -> defaultShape
    }
    val color = when {
        data.selectedBetween -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primary
    }

    val disabledModifier = Modifier
        .aspectRatio(1f, true)
        .clip(defaultShape)
        .background(MaterialTheme.colorScheme.surfaceVariant)

    val selectedModifier = Modifier
        .aspectRatio(1f, true)
        .clip(shape)
        .background(color)
        .clickable { data.date?.let { onDateClick(it) } }

    val normalModifier = Modifier
        .aspectRatio(1f, true)
        .clip(defaultShape)
        .clickable { data.date?.let { onDateClick(it) } }

    val otherMonthModifier = Modifier
        .aspectRatio(1f, true)

    val textStyle =
        when {
            data.disabled -> MaterialTheme.typography.bodySmall
            data.selectedBetween || data.selected -> MaterialTheme.typography.bodySmall.copy(
                MaterialTheme.colorScheme.onPrimary
            )

            today -> MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            else -> MaterialTheme.typography.bodySmall
        }

    val parentModifier = when (selection) {
        is CalendarSelection.Date -> Modifier.padding(dimensionResource(RC.dimen.scd_small_25))
        is CalendarSelection.Dates -> Modifier.padding(dimensionResource(RC.dimen.scd_small_25))
        is CalendarSelection.Period -> Modifier.padding(vertical = dimensionResource(RC.dimen.scd_small_25))
    }.testTags(
        TestTags.CALENDAR_DATE,
        data.date?.format(DateTimeFormatter.ISO_DATE) ?: ""
    )

    val cellModifier = when {
        data.otherMonth || data.disabledPassively -> otherMonthModifier
        data.disabled -> disabledModifier
        data.selected -> selectedModifier
        else -> normalModifier
    }

    val textAlpha = when {
        data.disabledPassively -> Constants.DATE_ITEM_DISABLED_TIMELINE_OPACITY
        else -> Constants.DATE_ITEM_OPACITY
    }

    Column(modifier = parentModifier) {
        Row(
            modifier = cellModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .alpha(textAlpha),
                text = data.date?.format(DateTimeFormatter.ofPattern("d"))
                    ?.takeUnless { data.otherMonth } ?: "",
                style = textStyle,
                textAlign = TextAlign.Center
            )
        }
    }
}