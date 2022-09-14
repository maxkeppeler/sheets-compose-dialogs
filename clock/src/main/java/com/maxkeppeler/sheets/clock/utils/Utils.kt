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
package com.maxkeppeler.sheets.clock.utils

import androidx.annotation.RestrictTo
import androidx.compose.runtime.MutableState
import com.maxkeppeler.sheets.clock.models.ClockConfig
import java.time.LocalTime

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun isAm(currentTime: LocalTime): Boolean {
    return currentTime.hour <= 12
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun convertTimeIntoTimeTextValues(
    is24hourFormat: Boolean,
    allowSeconds: Boolean,
    currentTime: LocalTime
): List<StringBuilder> {

    val timeTextValues = mutableListOf<StringBuilder>()

    val hours = if (is24hourFormat) {
        currentTime.hour
    } else {
        var hours = if (!isAm(currentTime)) currentTime.hour.minus(12) else currentTime.hour
        if(hours == 0) hours += 12 else hours
        hours
    }

    timeTextValues.add(StringBuilder(hours.toString().padStart(2, '0')))
    timeTextValues.add(StringBuilder(currentTime.minute.toString().padStart(2, '0')))

    if (allowSeconds) {
        timeTextValues.add(StringBuilder(currentTime.second.toString().padStart(2, '0')))
    }

    return timeTextValues
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun convertTimeTextValuesIntoTime(
    is24HourFormat: Boolean,
    isAm: Boolean,
    timeValueUnits: List<StringBuilder>
): LocalTime {

    val hour = timeValueUnits[0].toString().toInt()
    val min = timeValueUnits[1].toString().toInt()
    val sec = timeValueUnits.getOrNull(2)?.toString()?.toInt() ?: 0

    var actualHour = hour

    if (!is24HourFormat) {
        if (isAm && actualHour >= 12 && min > 0) actualHour -= 12
        else if (!isAm && ((actualHour < 12 && min >= 0) || (actualHour == 12 && min == 0))) actualHour += 12
    }
    if (actualHour == 24) actualHour = 0

    return LocalTime.of(actualHour, min, sec)
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun getInputKeys(config: ClockConfig): List<String> {
    return mutableListOf(
        *(1..9).toList().map { it.toString() }.toTypedArray(),
        Constants.ACTION_PREV,
        "0",
        Constants.ACTION_NEXT
    )
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun getDisabledInputKeys(
    timeValues: List<StringBuilder>,
    is24hourFormat: Boolean,
    index: Int,
    groupIndex: Int
): List<String> {

    val hourBuffer = timeValues[0]
    val minBuffer = timeValues[1]
    val secBuffer = timeValues.getOrNull(2)

    val isHours = groupIndex == 0

    return when {

        isHours && index == 0 -> null
        isHours && index == 1 -> {
            if (is24hourFormat) {
                if (hourBuffer[0] == '2') 4..9
                else null
            } else {
                if (hourBuffer[0] == '1' || hourBuffer[0] == '2') 3..9
                else if (hourBuffer[0] == '0') 0..0
                else null
            }
        }
        !isHours && index == 0 -> {
            if (hourBuffer[0] == '2' && hourBuffer[1] == '4') {
                0..9
            } else {
                6..9
            }
        }
        !isHours && index == 1 -> null
        else -> null
    }?.map { it.toString() } ?: listOf()
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun moveToPreviousIndex(
    valueIndex: MutableState<Int>,
    groupIndex: MutableState<Int>,
    maxGroupIndex: Int
) {
    when {
        valueIndex.value == 1 -> valueIndex.value = valueIndex.value.minus(1)
        valueIndex.value < 1 && groupIndex.value > 0 -> {
            valueIndex.value = 1
            groupIndex.value = groupIndex.value.minus(1)
        }
        else -> {
            valueIndex.value = 1
            groupIndex.value = maxGroupIndex
        }
    }
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun moveToNextIndex(
    valueIndex: MutableState<Int>,
    groupIndex: MutableState<Int>,
    maxGroupIndex: Int
) {
    when {
        valueIndex.value == 0 -> valueIndex.value = valueIndex.value.plus(1)
        valueIndex.value >= 1 && groupIndex.value < maxGroupIndex -> {
            valueIndex.value = 0
            groupIndex.value = groupIndex.value.plus(1)
        }
        else -> {
            valueIndex.value = 0
            groupIndex.value = 0
        }
    }
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun inputValue(
    is24hourFormat: Boolean,
    timeValues: List<StringBuilder>,
    groupIndex: MutableState<Int>,
    currentIndex: MutableState<Int>,
    newValue: Int,
    onNextIndex: () -> Unit
): List<StringBuilder> {

    val hourBuffer = timeValues[0]
    val minBuffer = timeValues[1]
    val secBuffer = timeValues.getOrNull(2)

    when (groupIndex.value) {
        0 -> {
            if (is24hourFormat) {
                if (currentIndex.value == 0 && newValue >= 3 && newValue <= 9) {
                    hourBuffer[currentIndex.value] = 0.digitToChar()
                    onNextIndex()
                    hourBuffer[currentIndex.value] = newValue.toString()[0]
                } else {
                    if (currentIndex.value == 0 && newValue != 0 && Character.getNumericValue(
                            hourBuffer[1]
                        ) > 3
                    ) {
                        hourBuffer[1] = 0.digitToChar()
                    }
                    hourBuffer[currentIndex.value] = newValue.toString()[0]
                }
                repeat(2) { minBuffer.deleteCharAt(0) }
                repeat(2) { minBuffer.append(0.digitToChar()) }
                repeat(2) { secBuffer?.deleteCharAt(0) }
                repeat(2) { secBuffer?.append(0.digitToChar()) }
            } else {
                if (currentIndex.value == 0 && newValue >= 2 && newValue <= 9) {
                    hourBuffer[currentIndex.value] = 0.digitToChar()
                    onNextIndex()
                    hourBuffer[currentIndex.value] = newValue.toString()[0]
                } else {
                    if (currentIndex.value == 0) {
                        if (newValue != 0 && Character.getNumericValue(hourBuffer[1]) > 2) {
                            hourBuffer[1] = 0.digitToChar()
                        } else if (newValue == 0 && Character.getNumericValue(hourBuffer[1]) == 0) {
                            hourBuffer[1] = 1.digitToChar()
                        }
                    }
                    hourBuffer[currentIndex.value] = newValue.toString()[0]
                }
                repeat(2) { minBuffer.deleteCharAt(0) }
                repeat(2) { minBuffer.append(0.digitToChar()) }
                repeat(2) { secBuffer?.deleteCharAt(0) }
                repeat(2) { secBuffer?.append(0.digitToChar()) }
            }
        }
        1 -> {
            minBuffer[currentIndex.value] = newValue.toString()[0]
        }
        2 -> secBuffer?.set(currentIndex.value, newValue.toString()[0])
    }

    onNextIndex()
    return arrayOf(hourBuffer, minBuffer, secBuffer).filterNotNull()
}
