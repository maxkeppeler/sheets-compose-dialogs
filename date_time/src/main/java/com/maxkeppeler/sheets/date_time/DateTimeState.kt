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
package com.maxkeppeler.sheets.date_time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.date_time.models.*
import com.maxkeppeler.sheets.date_time.utils.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

/**
 * Handles the date time state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class DateTimeState(
    val selection: DateTimeSelection,
    val config: DateTimeConfig,
    stateData: DateTimeStateData? = null
) : BaseTypeState() {

    var dateSelection by mutableStateOf<LocalDate?>(stateData?.dateSelection)
    var timeSelection by mutableStateOf<LocalTime?>(stateData?.timeSelection)
    var valid by mutableStateOf(isValid())

    private var datePattern by mutableStateOf(getDatePatternValue())
    private var timePattern by mutableStateOf(getTimePatternValue())

    private var typeValues = stateData?.typeValues ?: getInitTypeValues()

    var dateValues by mutableStateOf(getLocalizedValues(config, datePattern, typeValues))
    var timeValues by mutableStateOf(getLocalizedValues(config, timePattern, typeValues))

    var isDateValid by mutableStateOf(checkDateValid())
    var isTimeValid by mutableStateOf(checkTimeValid())

    private fun getInitTypeValues(): MutableMap<UnitType, UnitOptionEntry?> = mutableMapOf(
        // Date
        UnitType.DAY to null,
        UnitType.MONTH to null,
        UnitType.YEAR to null,
        // Time
        UnitType.SECOND to null,
        UnitType.MINUTE to null,
        UnitType.HOUR to null,
        UnitType.AM_PM to getAmPmOptions().first()
    )

    fun updateValue(unit: UnitSelection, entry: UnitOptionEntry) {
        unit.type?.let { type ->
            typeValues[type] = entry
            if (type.isDate) {
                dateValues = getLocalizedValues(config, datePattern, typeValues)
                isDateValid = checkDateValid()
            } else {
                timeValues = getLocalizedValues(config, timePattern, typeValues)
                isTimeValid = checkTimeValid()
            }
        }
        checkValid()
    }

    private fun checkDateValid(): Boolean {
        if (datePattern == null) return false
        val tmpDateValues = typeValues.values.take(3)
        val valid = tmpDateValues.all { it != null }
        if (valid) {
            val date = getLocalDateOf(tmpDateValues)
            date?.let {
                dateSelection = it
            } ?: run { typeValues[UnitType.DAY] = null } // Reset day field to provoke new selection
        } else {
            dateSelection = null
        }
        return valid
    }

    private fun checkTimeValid(): Boolean {
        if (timePattern == null) return false
        val tmpTimeValues = typeValues.values.drop(3)
        val secondsValid = !containsSeconds(timePattern!!) || tmpTimeValues.take(3).last() != null
        val valid = secondsValid && tmpTimeValues.take(3).drop(1).all { it != null }
        val time = if (valid) getLocalTimeOf(
            tmpTimeValues.lastOrNull()?.value == 0,
            tmpTimeValues
        ) else null
        timeSelection = time
        return valid
    }

    private fun getDatePatternValue(): String? = selection.dateFormatStyle?.let {
        getLocalizedPattern(
            isDate = true,
            locale = selection.locale,
            formatStyle = it
        )
    }

    private fun getTimePatternValue(): String? = selection.timeFormatStyle?.let {
        getLocalizedPattern(
            isDate = false,
            locale = selection.locale,
            formatStyle = it
        )
    }

    private fun checkValid() {
        valid = isValid()
    }

    private fun isValid(): Boolean = when (selection) {
        is DateTimeSelection.Date -> dateSelection != null
        is DateTimeSelection.Time -> timeSelection != null
        is DateTimeSelection.DateTime -> dateSelection != null && timeSelection != null
    }


    fun onFinish() {
        when (selection) {
            is DateTimeSelection.Date -> selection.onPositiveClick(dateSelection!!)
            is DateTimeSelection.Time -> selection.onPositiveClick(timeSelection!!)
            is DateTimeSelection.DateTime -> {
                val value = dateSelection!!.atTime(timeSelection!!)
                selection.onPositiveClick(value)
            }
        }
    }

    override fun reset() {
        dateSelection = null
        timeSelection = null
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: DateTimeSelection,
            config: DateTimeConfig
        ): Saver<DateTimeState, *> = Saver(
            save = { state ->
                DateTimeStateData(
                    state.dateSelection,
                    state.timeSelection,
                    state.typeValues
                )
            },
            restore = { data -> DateTimeState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class DateTimeStateData(
        val dateSelection: LocalDate?,
        val timeSelection: LocalTime?,
        val typeValues: MutableMap<UnitType, UnitOptionEntry?>
    ) : Serializable
}

/**
 * Create a DateTimeState and remember it.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberDateTimeState(
    selection: DateTimeSelection,
    config: DateTimeConfig,
): DateTimeState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = DateTimeState.Saver(selection, config),
    init = { DateTimeState(selection, config) }
)