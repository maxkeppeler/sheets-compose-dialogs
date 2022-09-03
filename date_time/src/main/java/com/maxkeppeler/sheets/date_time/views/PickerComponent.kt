package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.DateTextConfig
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.date_time.utils.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.chrono.Chronology
import java.time.format.FormatStyle
import java.util.*

@Composable
internal fun PickerComponent(
    config: DateTextConfig,
    locale: Locale = Locale.getDefault(),
    formatStyle: FormatStyle,
    chronology: Chronology = Chronology.ofLocale(locale),
    onDateValueChange: ((LocalDate?) -> Unit)? = null,
    onTimeValueChange: ((LocalTime?) -> Unit)? = null
) {

    val isDate = onDateValueChange != null
    val height = remember { mutableStateOf(0) }

    val pattern by remember {
        val value = getLocalizedPattern(isDate, formatStyle, chronology, locale)
        mutableStateOf(value)
    }

    val values = remember(pattern) {
        val values = getLocalizedValues(pattern)
        mutableStateListOf(*values)
    }

    var day by remember { mutableStateOf<UnitOptionEntry?>(null) }
    var month by remember { mutableStateOf<UnitOptionEntry?>(null) }
    var year by remember { mutableStateOf<UnitOptionEntry?>(null) }

    var sec by remember { mutableStateOf<UnitOptionEntry?>(null) }
    var min by remember { mutableStateOf<UnitOptionEntry?>(null) }
    var hour by remember { mutableStateOf<UnitOptionEntry?>(null) }
    var isAm by remember { mutableStateOf(if (is24HourFormat(pattern)) null else true) }

    if (isDate) {
        LaunchedEffect(day, month, year) {
            val valid = day != null && month != null && year != null
            if (valid) {
                val date = getLocalDateOf(day, month, year)
                date?.let { onDateValueChange?.invoke(date) }
                    ?: run { day = null } // Reset day field to provoke new selection
            } else onDateValueChange?.invoke(null)
        }
    } else {
        LaunchedEffect(sec, min, hour) {
            val secondsValid = !containsSeconds(pattern) || sec != null
            val valid = secondsValid && min != null && hour != null
            val time = if (valid) getLocalTimeOf(isAm, sec, min, hour) else null
            onTimeValueChange?.invoke(time)
        }
    }

    val onValueSelection: (UnitSelection, UnitOptionEntry) -> Unit = { unit, entry ->
        when (unit) {
            is UnitSelection.Day -> day = entry
            is UnitSelection.Month -> month = entry
            is UnitSelection.Year -> year = entry
            is UnitSelection.Hour -> hour = entry
            is UnitSelection.Minute -> min = entry
            is UnitSelection.Second -> sec = entry
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .onGloballyPositioned { coordinates ->
                    if (height.value < coordinates.size.height) {
                        height.value = coordinates.size.height
                    }
                },
            verticalAlignment = Alignment.Bottom
        ) {
            values.forEachIndexed { index, textPart ->
                val segments = getLocalizedValueSegments(textPart)
                segments.forEach { segment ->
                    if (!config.hideDateCharacters && segment.isEmpty()) {
                        PickerDateCharacterComponent(text = ",")
                    } else {
                        val unitSelection by remember(day, month, year, sec, min, hour) {
                            val unitValue = detectUnit(
                                config = config, pattern = pattern, segment = segment,
                                sec = sec, min = min, hour = hour,
                                day = day, month = month, year = year
                            )
                            mutableStateOf(unitValue)
                        }
                        unitSelection?.let { unit ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                UnitContainerComponent(
                                    unit = unit,
                                    height = height,
                                    onValueChange = { onValueSelection(unit, it) },
                                )
                                if (!config.hideTimeCharacters && !isDate
                                    && index < values.lastIndex
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .clip(MaterialTheme.shapes.extraSmall)
                                            .padding(start = 6.dp)
                                            .padding(top = 24.dp)
                                            .padding(end = 6.dp),
                                        text = ":"
                                    )
                                }
                            }
                        }
                    }
                }
            }
            isAm?.let {
                UnitContainerComponent(
                    unit = UnitSelection.AmPm(),
                    height = height,
                    onValueChange = {
                        isAm = it.value == 0
                    }
                )
            }
        }
    }
}