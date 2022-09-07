@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock

import android.text.format.DateFormat
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.utils.*
import com.maxkeppeler.sheets.clock.views.KeyboardComponent
import com.maxkeppeler.sheets.clock.views.TimeValueComponent
import java.time.LocalTime

/**
 * Clock view for the use-case to to select a clock time.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun ClockView(
    selection: ClockSelection,
    config: ClockConfig = ClockConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val context = LocalContext.current

    var time by rememberSaveable {
        val value = config.defaultTime ?: LocalTime.now()
        mutableStateOf(value)
    }

    val is24HourFormat by rememberSaveable {
        val value = config.is24HourFormat ?: DateFormat.is24HourFormat(context)
        mutableStateOf(value)
    }

    var isAm by rememberSaveable { mutableStateOf(isAm(time)) }
    val groupIndex = rememberSaveable { mutableStateOf(0) }
    val valueIndex = rememberSaveable { mutableStateOf(0) }

    var timeTextValues by remember {
        val parseTime = convertTimeIntoTimeTextValues(
            is24hourFormat = is24HourFormat,
            allowSeconds = selection is ClockSelection.HoursMinutesSeconds,
            currentTime = time
        )
        mutableStateOf(parseTime)
    }

    LaunchedEffect(isAm, valueIndex.value, groupIndex.value, timeTextValues) {
        time = convertTimeTextValuesIntoTime(
            isAm = isAm,
            is24HourFormat = is24HourFormat,
            timeValueUnits = timeTextValues
        )
    }

    val onPrevAction = {
        moveToPreviousIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
    }

    val onNextAction = {
        moveToNextIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
    }

    val onEnterValue: (Int) -> Unit = { value ->
        timeTextValues = inputValue(
            timeValues = timeTextValues,
            is24hourFormat = is24HourFormat,
            currentIndex = valueIndex,
            groupIndex = groupIndex,
            newValue = value,
            onNextIndex = onNextAction
        )
    }

    val keys by rememberSaveable { mutableStateOf(getInputKeys(config)) }
    val disabledKeys by remember(valueIndex.value, groupIndex.value) {
        val values = getDisabledInputKeys(
            is24hourFormat = is24HourFormat,
            timeValues = timeTextValues,
            index = valueIndex.value,
            groupIndex = groupIndex.value,
        )
        mutableStateOf(values)
    }

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(top = 24.dp),
        content = {
            TimeValueComponent(
                unitValues = timeTextValues,
                isAm = isAm,
                is24hourFormat = is24HourFormat,
                valueIndex = valueIndex.value,
                groupIndex = groupIndex.value,
                onGroupClick = {
                    valueIndex.value = 0
                    groupIndex.value = it
                },
                onAm = { isAm = it },
            )

            KeyboardComponent(
                keys = keys,
                disabledKeys = disabledKeys,
                onEnterValue = onEnterValue,
                onPrevAction = onPrevAction,
                onNextAction = onNextAction
            )
        },
        buttons = {
            ButtonsComponent(
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    when (selection) {
                        is ClockSelection.HoursMinutes ->
                            selection.onPositiveClick(time.hour, time.minute)
                        is ClockSelection.HoursMinutesSeconds ->
                            selection.onPositiveClick(time.hour, time.minute, time.second)
                    }
                    onCancel()
                }
            )
        }
    )
}

