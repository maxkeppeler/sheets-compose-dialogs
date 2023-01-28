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
@file:OptIn(ExperimentalAnimationGraphicsApi::class)

package com.maxkeppeler.sheets.calendar.views

import androidx.compose.animation.*
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.calendar.R
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.maxkeppeler.sheets.core.R as RC

/**
 * Top header component of the calendar dialog.
 * @param config The general configuration for the dialog.
 * @param mode The display mode of the dialog.
 * @param navigationDisabled Whenever the navigation of the navigation is disabled.
 * @param prevDisabled Whenever the navigation to the previous period is disabled.
 * @param nextDisabled Whenever the navigation to the next period is disabled.
 * @param cameraDate The current camera-date of the month view.
 * @param onPrev The listener that is invoked when the navigation to the previous period is invoked.
 * @param onNext The listener that is invoked when the navigation to the next period is invoked.
 * @param onMonthClick The listener that is invoked when the month selection was clicked.
 * @param onYearClick The listener that is invoked when the year selection was clicked.
 */
@ExperimentalMaterial3Api
@Composable
internal fun CalendarTopComponent(
    config: CalendarConfig,
    mode: CalendarDisplayMode,
    navigationDisabled: Boolean,
    prevDisabled: Boolean,
    nextDisabled: Boolean,
    cameraDate: LocalDate,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onMonthClick: () -> Unit,
    onYearClick: () -> Unit,
) {

    val enterTransition = expandIn(expandFrom = Alignment.Center, clip = false) + fadeIn()
    val exitTransition = shrinkOut(shrinkTowards = Alignment.Center, clip = false) + fadeOut()

    val chevronAVD = AnimatedImageVector.animatedVectorResource(R.drawable.avd_chevron_down_up)
    var chevronMonthAtEnd by remember { mutableStateOf(false) }
    var chevronYearAtEnd by remember { mutableStateOf(false) }

    LaunchedEffect(mode) {
        when (mode) {
            CalendarDisplayMode.CALENDAR -> {
                chevronMonthAtEnd = false
                chevronYearAtEnd = false
            }
            CalendarDisplayMode.MONTH -> chevronYearAtEnd = false
            CalendarDisplayMode.YEAR -> chevronMonthAtEnd = false
        }
    }

    val selectableContainerModifier = Modifier.clip(MaterialTheme.shapes.extraSmall)
    val selectableItemModifier = Modifier
        .padding(start = dimensionResource(RC.dimen.scd_small_100))
        .padding(vertical = dimensionResource(RC.dimen.scd_small_50))
        .padding(end = dimensionResource(RC.dimen.scd_small_50))

    Box(modifier = Modifier.fillMaxWidth()) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterStart),
            visible = !navigationDisabled && !prevDisabled,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Column(Modifier.align(Alignment.CenterStart)) {
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier
                        .size(dimensionResource(RC.dimen.scd_size_200)),
                    enabled = !navigationDisabled && !prevDisabled,
                    onClick = onPrev
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        imageVector = config.icons.ChevronLeft,
                        contentDescription = stringResource(
                            when (config.style) {
                                CalendarStyle.MONTH -> R.string.scd_calendar_dialog_prev_month
                                CalendarStyle.WEEK -> R.string.scd_calendar_dialog_prev_week
                            }
                        )
                    )
                }

            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = selectableContainerModifier
                    .clickable(config.monthSelection) {
                        if (config.monthSelection) {
                            chevronMonthAtEnd = !chevronMonthAtEnd
                        }
                        onMonthClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = selectableItemModifier,
                    text = cameraDate.format(DateTimeFormatter.ofPattern("MMM")),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                if (config.monthSelection) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        painter = rememberAnimatedVectorPainter(chevronAVD, chevronMonthAtEnd),
                        contentDescription = stringResource(R.string.scd_calendar_dialog_select_month),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = selectableContainerModifier
                    .clickable(config.yearSelection) {
                        if (config.yearSelection) {
                            chevronYearAtEnd = !chevronYearAtEnd
                        }
                        onYearClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = selectableItemModifier,
                    text = cameraDate.format(DateTimeFormatter.ofPattern("yyyy")),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                if (config.yearSelection) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        painter = rememberAnimatedVectorPainter(chevronAVD, chevronYearAtEnd),
                        contentDescription = stringResource(id = R.string.scd_calendar_dialog_select_year),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterEnd),
            visible = !navigationDisabled && !nextDisabled,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Column(Modifier.align(Alignment.CenterEnd)) {
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_200)),
                    enabled = !navigationDisabled && !nextDisabled,
                    onClick = onNext
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        imageVector = config.icons.ChevronRight,
                        contentDescription = stringResource(
                            when (config.style) {
                                CalendarStyle.MONTH -> R.string.scd_calendar_dialog_next_month
                                CalendarStyle.WEEK -> R.string.scd_calendar_dialog_next_week
                            }
                        )
                    )
                }
            }
        }
    }
}