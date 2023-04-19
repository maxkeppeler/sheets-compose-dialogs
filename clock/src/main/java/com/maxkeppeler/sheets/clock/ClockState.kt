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
package com.maxkeppeler.sheets.clock

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.models.base.Debouncer
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.utils.*
import com.maxkeppeler.sheets.clock.utils.isAm
import java.io.Serializable
import java.time.LocalTime

/**
 * Handles the clock state.
 * @param context The context that is used to check if the 24hFormat is used.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class ClockState(
    private val context: Context,
    val selection: ClockSelection,
    val config: ClockConfig,
    stateData: ClockStateData? = null
) : BaseTypeState() {

    var groupIndex = mutableStateOf(stateData?.groupIndex ?: 0)
    var valueIndex = mutableStateOf(stateData?.valueIndex ?: 0)
    var is24HourFormat by mutableStateOf(stateData?.is24HourFormat ?: isInit24HourFormat())
    var time by mutableStateOf(stateData?.time ?: getInitTime())
    var isAm by mutableStateOf(stateData?.isAm ?: isAm(time))
    var timeTextValues by mutableStateOf(getTimeInTextValues())
    val keys by mutableStateOf(getInputKeys())
    var disabledKeys by mutableStateOf(getCurrentDisabledKeys())
    var valid by mutableStateOf(isValid())

    init {
        checkSetup()
    }

    private fun checkSetup() {
        if (config.defaultTime != null && config.boundary != null && (config.defaultTime !in config.boundary))
            throw IllegalStateException("Please correct your setup. The default time must be in the boundary.")
        if(config.boundary != null && config.boundary.start > config.boundary.endInclusive)
            throw IllegalStateException("Please correct your setup. The start time must be before the end time.")
    }

    private val debouncer = Debouncer(Constants.DEBOUNCE_KEY_CLICK_DURATION)

    private fun isValid(): Boolean = config.boundary?.let { time in it } ?: true

    private fun isInit24HourFormat(): Boolean {
        return config.is24HourFormat ?: DateFormat.is24HourFormat(context)
    }

    private fun getInitTime(): LocalTime {
        return config.defaultTime ?: LocalTime.now()
    }

    private fun getTimeInTextValues(): List<StringBuilder> {
        return convertTimeIntoTimeTextValues(
            is24hourFormat = is24HourFormat,
            allowSeconds = selection is ClockSelection.HoursMinutesSeconds,
            currentTime = time
        )
    }

    private fun getTimeOfTextValues(): LocalTime {
        return convertTimeTextValuesIntoTime(
            isAm = isAm,
            is24HourFormat = is24HourFormat,
            timeValueUnits = timeTextValues
        )
    }

    private fun getCurrentDisabledKeys(): List<String> {
        return getDisabledInputKeys(
            is24hourFormat = is24HourFormat,
            timeValues = timeTextValues,
            index = valueIndex.value,
            groupIndex = groupIndex.value,
        )
    }

    private fun refreshDisabledKeys() {
        disabledKeys = getCurrentDisabledKeys()
    }

    private fun refreshTimeValue() {
        time = getTimeOfTextValues()
        checkValid()
    }

    private fun checkValid() {
        valid = isValid()
    }

    fun onChange12HourFormatValue(newIsAm: Boolean) {
        isAm = newIsAm
        refreshDisabledKeys()
        refreshTimeValue()
    }

    fun onEnterValue(value: Int) {
        debouncer.debounce { // https://github.com/maxkeppeler/sheets-compose-dialogs/issues/28
            timeTextValues = inputValue(
                timeValues = timeTextValues,
                is24hourFormat = is24HourFormat,
                currentIndex = valueIndex,
                groupIndex = groupIndex,
                newValue = value,
                onNextIndex = this::onNextAction
            )
            refreshDisabledKeys()
            refreshTimeValue()
        }
    }

    fun onPrevAction() {
        moveToPreviousIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
        refreshDisabledKeys()
    }

    fun onNextAction() {
        moveToNextIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
        refreshDisabledKeys()
    }

    fun onValueGroupClick(newGroupIndex: Int) {
        valueIndex.value = 0
        groupIndex.value = newGroupIndex
        refreshDisabledKeys()
    }

    fun onFinish() {
        when (selection) {
            is ClockSelection.HoursMinutes ->
                selection.onPositiveClick(time.hour, time.minute)
            is ClockSelection.HoursMinutesSeconds ->
                selection.onPositiveClick(time.hour, time.minute, time.second)
        }
    }

    override fun reset() {
        groupIndex.value = 0
        valueIndex.value = 0
        is24HourFormat = isInit24HourFormat()
        time = getInitTime()
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param context The context that is used to resolve the colors.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            context: Context,
            selection: ClockSelection,
            config: ClockConfig
        ): Saver<ClockState, *> = Saver(
            save = { state ->
                ClockStateData(
                    groupIndex = state.groupIndex.value,
                    valueIndex = state.valueIndex.value,
                    is24HourFormat = state.is24HourFormat,
                    time = state.time,
                    isAm = state.isAm
                )
            },
            restore = { data -> ClockState(context, selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class ClockStateData(
        val groupIndex: Int,
        val valueIndex: Int,
        val is24HourFormat: Boolean,
        val time: LocalTime,
        val isAm: Boolean
    ) : Serializable
}

/**
 * Create a ClockState and remember it.
 * @param context The context that is used to check if the 24hFormat is used.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberClockState(
    context: Context,
    selection: ClockSelection,
    config: ClockConfig,
): ClockState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = ClockState.Saver(context, selection, config),
    init = { ClockState(context, selection, config) }
)