package com.maxkeppeler.sheets.calendar.views

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarDisplayMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.maxkeppeler.sheets.core.R as RC

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
        .padding(start = 8.dp)
        .padding(vertical = 4.dp)
        .padding(end = 4.dp)

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
                        .size(dimensionResource(RC.dimen.size_200)),
                    onClick = onPrev
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                        imageVector = Icons.Rounded.ChevronLeft,
                        contentDescription = "Last month"
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
                    text = cameraDate.format(DateTimeFormatter.ofPattern("MMMM")),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                if (config.monthSelection) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                        imageVector = if (mode == CalendarDisplayMode.MONTH) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                        contentDescription = "Select month",
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
                        modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                        imageVector = if (mode == CalendarDisplayMode.MONTH) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                        contentDescription = "Select year",
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
                    modifier = Modifier.size(dimensionResource(RC.dimen.size_200)),
                    onClick = onNext
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = "Next month"
                    )
                }
            }
        }
    }
}