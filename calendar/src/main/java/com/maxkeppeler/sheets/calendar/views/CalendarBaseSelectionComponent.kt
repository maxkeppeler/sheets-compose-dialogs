@file:OptIn(ExperimentalSnapperApi::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import com.maxkeppeler.sheets.calendar.models.CalendarSwipeAction
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
internal fun CalendarBaseSelectionComponent(
    modifier: Modifier,
    yearListState: LazyListState,
    calendarGridState: LazyGridState,
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
        .padding(top = 16.dp)

    val dateModifier = baseModifier.swipeable(
        state = swipeableState,
        anchors = anchors,
        thresholds = { _, _ -> FractionalThreshold(0.5f) },
        orientation = Orientation.Horizontal
    )
    val monthModifier = baseModifier
    val yearModifier = baseModifier
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

    when (mode) {
        CalendarDisplayMode.CALENDAR -> {
            LazyVerticalGrid(
                state = calendarGridState,
                columns = GridCells.Fixed(cells),
                modifier = dateModifier,
            ) {
                onCalendarView()
            }
        }
        CalendarDisplayMode.YEAR -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select year",
                    style = MaterialTheme.typography.titleMedium,
                )
                LazyRow(
                    modifier = yearModifier,
                    state = yearListState,
                    flingBehavior = rememberSnapperFlingBehavior(yearListState)
                ) {
                    onYearView()
                }
            }
        }
        CalendarDisplayMode.MONTH -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(cells),
                modifier = monthModifier,
            ) {
                onMonthView()
            }
        }
    }
}