@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.clock

import android.text.format.DateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.clock.models.ClockTimeConfig
import com.maxkeppeler.sheets.clock.models.ClockTimeSelection
import com.maxkeppeler.sheets.clock.utils.*
import com.maxkeppeler.sheets.clock.views.TimeInputComponent
import com.maxkeppeler.sheets.clock.views.TimeValueComponent
import java.time.LocalTime

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun ClockView(
    selection: ClockTimeSelection,
    config: ClockTimeConfig = ClockTimeConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    val context = LocalContext.current

    var time by rememberSaveable { mutableStateOf(config.currentTime ?: LocalTime.now()) }

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
            allowSeconds = selection is ClockTimeSelection.HoursMinutesSeconds,
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

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 24.dp),
    ) {

        HeaderComponent(header)

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

        TimeInputComponent(
            keys = keys,
            disabledKeys = disabledKeys,
            onEnterValue = onEnterValue,
            onPrevAction = onPrevAction,
            onNextAction = onNextAction
        )

        ButtonComponent(
            negativeButtonText = selection.negativeButtonText,
            positiveButtonText = selection.positiveButtonText,
            onNegative = { selection.onNegativeClick?.invoke(); onCancel() },
            onPositive = {
                when (selection) {
                    is ClockTimeSelection.HoursMinutes ->
                        selection.onPositiveClick(time.hour, time.minute)
                    is ClockTimeSelection.HoursMinutesSeconds ->
                        selection.onPositiveClick(time.hour, time.minute, time.second)
                }
                onCancel()
            }
        )
    }
}

