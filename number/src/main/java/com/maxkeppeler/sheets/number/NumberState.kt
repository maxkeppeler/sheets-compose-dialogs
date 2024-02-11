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
package com.maxkeppeler.sheets.number

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.models.base.Debouncer
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.number.models.NumberConfig
import com.maxkeppeler.sheets.number.models.NumberSelection
import com.maxkeppeler.sheets.number.utils.Constants
import com.maxkeppeler.sheets.number.utils.ValueType
import com.maxkeppeler.sheets.number.utils.convertTimeIntoTimeTextValues
import com.maxkeppeler.sheets.number.utils.convertTimeTextValuesIntoTime
import com.maxkeppeler.sheets.number.utils.getInputKeys
import com.maxkeppeler.sheets.number.utils.getValuePairs
import com.maxkeppeler.sheets.number.utils.inputValue
import com.maxkeppeler.sheets.number.utils.moveToNextIndex
import com.maxkeppeler.sheets.number.utils.moveToPreviousIndex
import com.maxkeppeler.sheets.number.utils.parseToTime
import java.io.Serializable

/**
 * Handles the state of the number view.
 * @param context The context that is used to check if the 24hFormat is used.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class NumberState(
    private val context: Context,
    val selection: NumberSelection,
    val config: NumberConfig,
    stateData: NumberStateData? = null
) : BaseTypeState() {

    var groupIndex = mutableIntStateOf(stateData?.groupIndex ?: 0)
    var valueIndex = mutableIntStateOf(stateData?.valueIndex ?: 0)
    val keys by mutableStateOf(getInputKeys())

    var number = mutableStateOf(stateData?.number ?: getInitNumber())
    var timeTextValues by mutableStateOf(getTimeInTextValues())
    var values by mutableStateOf(getInitValuePairs())
    var indexOfFirstValue by mutableStateOf(getInitIndexOfFirstValue())
    var valid by mutableStateOf(isValid())

    init {
        checkSetup()
    }

    private fun checkSetup() {
        when (selection) {
            is NumberSelection.Decimal -> {
                if (selection.maxValue <= 0)
                    throw IllegalStateException("Please correct your setup. The max value must be greater than 0.")
                if (selection.maxValue <= selection.minValue)
                    throw IllegalStateException("Please correct your setup. The max value must be greater than the min value.")
                if (selection.defaultValue != null && (selection.defaultValue < selection.minValue || selection.defaultValue > selection.maxValue))
                    throw IllegalStateException("Please correct your setup. The default value must be within the min and max value.")
            }

            is NumberSelection.Integer -> {
                if (selection.maxValue <= 0)
                    throw IllegalStateException("Please correct your setup. The max value must be greater than 0.")
                if (selection.maxValue <= selection.minValue)
                    throw IllegalStateException("Please correct your setup. The max value must be greater than the min value.")
                if (selection.defaultValue != null && (selection.defaultValue < selection.minValue || selection.defaultValue > selection.maxValue))
                    throw IllegalStateException("Please correct your setup. The default value must be within the min and max value.")
            }
        }
    }

    private fun getInitNumber(): Any = when (selection) {
        is NumberSelection.Decimal -> selection.defaultValue ?: 0L
        is NumberSelection.Integer -> selection.defaultValue ?: 0
    }

    private fun getInitIndexOfFirstValue(): Int? =
        values
            .indexOfFirst { runCatching { it.first.toInt() != 0 }.getOrNull() ?: false }
            .takeUnless { it == -1 }

    private fun getInitValuePairs(): List<Pair<String, ValueType>> {
        return getValuePairs(selection)
    }

    private val debouncer = Debouncer(Constants.DEBOUNCE_KEY_CLICK_DURATION)

    private fun isValid(): Boolean = true

    private fun getTimeInTextValues(): List<String> =
        convertTimeIntoTimeTextValues(
            number = number.value,
            selection = selection
        )

    private fun <T> getNumberOfTextValues(): T =
        convertTimeTextValuesIntoTime(
            selection = selection,
            timeValueUnits = timeTextValues
        )

    private fun refreshTimeValue() {
        number.value = getNumberOfTextValues()
        checkValid()
    }

    private fun checkValid() {
        valid = isValid()
    }

    fun onEnterValue(value: Int) {
        debouncer.debounce { // https://github.com/maxkeppeler/sheets-compose-dialogs/issues/28
            timeTextValues = inputValue(
                timeValues = timeTextValues,
                groupIndex = groupIndex,
                currentIndex = valueIndex,
                newValue = value
            )
            refreshTimeValue()
        }
    }

    fun onPrevAction() {
        moveToPreviousIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
    }

    fun onNextAction() {
        moveToNextIndex(
            valueIndex = valueIndex,
            groupIndex = groupIndex,
            maxGroupIndex = timeTextValues.lastIndex
        )
    }

    fun onValueGroupClick(newGroupIndex: Int) {
        valueIndex.value = 0
        groupIndex.value = newGroupIndex
    }

    fun onFinish() {
        when (selection) {
            is NumberSelection.Integer ->
                selection.onPositiveClick(parseToTime(values, selection))

            is NumberSelection.Decimal ->
                selection.onPositiveClick(parseToTime(values, selection))
        }
    }

    override fun reset() {
        groupIndex.value = 0
        valueIndex.value = 0
        number.value = getInitNumber()
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
            selection: NumberSelection,
            config: NumberConfig
        ): Saver<NumberState, *> = Saver(
            save = { state ->
                NumberStateData(
                    groupIndex = state.groupIndex.intValue,
                    valueIndex = state.valueIndex.intValue,
                    number = state.number.value,
                )
            },
            restore = { data -> NumberState(context, selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class NumberStateData(
        val number: Any,
        val groupIndex: Int,
        val valueIndex: Int,
    ) : Serializable
}

/**
 * Create a NumberState and remembers it.
 * @param context The context that is used to check if the 24hFormat is used.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberNumberState(
    context: Context,
    selection: NumberSelection,
    config: NumberConfig,
): NumberState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = NumberState.Saver(context, selection, config),
    init = { NumberState(context, selection, config) }
)