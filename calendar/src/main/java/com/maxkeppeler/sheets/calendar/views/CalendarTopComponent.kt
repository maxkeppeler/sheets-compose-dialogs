package com.maxkeppeler.sheets.calendar.views

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

    val enterTransition = fadeIn() + expandIn(expandFrom = Alignment.Center, clip = false)
    val exitTransition = shrinkOut(shrinkTowards = Alignment.Center, clip = false) + fadeOut()

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
                    onClick = onPrev
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        imageVector = Icons.Rounded.ChevronLeft,
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
                    .clickable(config.monthSelection) { onMonthClick() },
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
                        imageVector = if (mode == CalendarDisplayMode.MONTH) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                        contentDescription = stringResource(R.string.scd_calendar_dialog_select_month),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = selectableContainerModifier
                    .clickable(config.yearSelection) { onYearClick() },
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
                        imageVector = if (mode == CalendarDisplayMode.MONTH) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
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
                    onClick = onNext
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        imageVector = Icons.Rounded.ChevronRight,
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