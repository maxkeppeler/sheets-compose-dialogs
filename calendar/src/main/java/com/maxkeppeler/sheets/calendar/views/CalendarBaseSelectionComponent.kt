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
@file:OptIn(ExperimentalSnapperApi::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.calendar.R
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import com.maxkeppeler.sheets.calendar.models.CalendarSwipeAction
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import com.maxkeppeler.sheets.core.R as RC

/**
 * The main component for the selection of the use-case as well as the selection of month and year within the dialog.
 * @param modifier The modifier that is applied to this component.
 * @param yearListState The state of the year list selection view.
 * @param cells The amount of cells / columns that are used for the calendar grid view.
 * @param mode The display mode of the dialog.
 * @param nextDisabled Whenever the navigation to the next period is disabled.
 * @param prevDisabled Whenever the navigation to the previous period is disabled.
 * @param onPrev The listener that is invoked when the navigation to the previous period is invoked.
 * @param onNext The listener that is invoked when the navigation to the next period is invoked.
 * @param onCalendarView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.CALENDAR].
 * @param onMonthView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.MONTH].
 * @param onYearView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.YEAR].
 */
@Composable
internal fun CalendarBaseSelectionComponent(
    modifier: Modifier,
    yearListState: LazyListState,
    cells: Int,
    mode: CalendarDisplayMode,
    nextDisabled: Boolean,
    prevDisabled: Boolean,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onCalendarView: LazyGridScope.() -> Unit,
    onMonthView: LazyGridScope.() -> Unit,
    onYearView: LazyListScope.() -> Unit
) {

    val swipeableState = rememberSwipeableState(CalendarSwipeAction.NONE)
    val anchors = mapOf(
        0f to CalendarSwipeAction.NEXT,
        150f to CalendarSwipeAction.NONE,
        300f to CalendarSwipeAction.PREV
    )

    when (swipeableState.currentValue) {
        CalendarSwipeAction.NEXT -> {
            LaunchedEffect(swipeableState.currentValue) {
                if (!nextDisabled) onNext()
                swipeableState.snapTo(CalendarSwipeAction.NONE)
            }
        }
        CalendarSwipeAction.PREV -> {
            LaunchedEffect(swipeableState.currentValue) {
                if (!prevDisabled) onPrev()
                swipeableState.snapTo(CalendarSwipeAction.NONE)
            }
        }
        CalendarSwipeAction.NONE -> Unit
    }

    val baseModifier = modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(RC.dimen.scd_normal_100))

    val gridDateModifier = baseModifier.swipeable(
        state = swipeableState,
        anchors = anchors,
        thresholds = { _, _ -> FractionalThreshold(0.5f) },
        orientation = Orientation.Horizontal
    )
    val gridYearModifier = baseModifier
        .graphicsLayer { alpha = 0.99F }
        .drawWithContent {
            val colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.25f to Color.Black,
                0.75f to Color.Black,
                1.0f to Color.Transparent
            )
            drawContent()
            drawRect(
                brush = Brush.horizontalGradient(*colorStops),
                blendMode = BlendMode.DstIn
            )
        }

    val behavior = rememberSnapperFlingBehavior(
        lazyListState = yearListState,
        snapOffsetForItem = SnapOffsets.Center,
    )

    when (mode) {
        CalendarDisplayMode.CALENDAR -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(cells),
                modifier = gridDateModifier,
                userScrollEnabled = false,
            ) {
                onCalendarView()
            }
        }
        CalendarDisplayMode.YEAR -> {
            Column(
                modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_normal_150)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.scd_calendar_dialog_select_year),
                    style = MaterialTheme.typography.titleMedium,
                )
                LazyRow(
                    modifier = gridYearModifier,
                    state = yearListState,
                    flingBehavior = behavior,
                    contentPadding = PaddingValues(horizontal = dimensionResource(RC.dimen.scd_large_100)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_50))
                ) {
                    onYearView()
                }
            }
        }
        CalendarDisplayMode.MONTH -> {
            Column(
                modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_normal_150)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.scd_calendar_dialog_select_month),
                    style = MaterialTheme.typography.titleMedium,
                )
                LazyVerticalGrid(
                    modifier = baseModifier,
                    columns = GridCells.Fixed(cells),
                ) {
                    onMonthView()
                }
            }
        }
    }
}