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
package com.maxkeppeler.sheets.number.utils

import androidx.annotation.RestrictTo
import androidx.compose.runtime.MutableState
import com.maxkeppeler.sheets.number.models.NumberSelection

enum class ValueType {
    INTEGER, DECIMAL
}

internal fun getValuePairs(
    selection: NumberSelection
): List<Pair<String, ValueType>> {

    val valuePairs = mutableListOf<Pair<String, ValueType>>()
    when (selection) {
        is NumberSelection.Integer -> {
            valuePairs.add(selection.defaultValue.toString() to ValueType.INTEGER)
        }

        is NumberSelection.Decimal -> {
            val beforeComma = selection.defaultValue.toString().substringBefore(".")
            val afterComma = selection.defaultValue.toString().substringAfter(".")
            valuePairs.add(beforeComma to ValueType.INTEGER)
            valuePairs.add(afterComma to ValueType.DECIMAL)
        }
    }
    return valuePairs
}

@Suppress("UNCHECKED_CAST")
internal fun <T> parseToTime(time: List<Pair<String, ValueType>>, selection: NumberSelection): T {
    return when (selection) {
        is NumberSelection.Integer -> {
            time.first().first.toInt() as T
        }

        is NumberSelection.Decimal -> {
            val beforeComma = time.first().first.toInt()
            val afterComma = time.last().first.toLong()
            val decimalValue = beforeComma + (afterComma / Math.pow(
                10.0,
                time.last().first.length.toDouble()
            )).toFloat()
            decimalValue as T
        }
    }
}


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun <T> convertTimeIntoTimeTextValues(
    selection: NumberSelection,
    number: T?,
): List<String> {
    val timeTextValues = mutableListOf<String>()
    when (selection) {
        is NumberSelection.Decimal -> {
            val beforeComma = number.toString().substringBefore(".")
            val afterComma = number.toString().substringAfter(".")
            timeTextValues.add(beforeComma)
            timeTextValues.add(afterComma.padEnd(selection.decimalPlaces, '0'))
        }

        is NumberSelection.Integer -> {
            timeTextValues.add(number.toString())
        }
    }
    return timeTextValues
}

@Suppress("UNCHECKED_CAST")
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun <T> convertTimeTextValuesIntoTime(
    selection: NumberSelection,
    timeValueUnits: List<String>
): T {

    val firstNumber = timeValueUnits.getOrNull(0)?.toInt()
    val secondNumber = timeValueUnits.getOrNull(1)?.toInt()

    return when (selection) {
        is NumberSelection.Decimal -> {
            val decimalIntegerPart = firstNumber?.toLong() ?: 0L
            val decimalFractionalPart = secondNumber?.toLong() ?: 0L
            val decimalNumber = decimalIntegerPart + (decimalFractionalPart / Math.pow(
                10.0,
                timeValueUnits[1].length.toDouble()
            )).toFloat()
            return decimalNumber as T
        }

        is NumberSelection.Integer -> (firstNumber ?: 0L) as T
    }
}

internal fun parseDefaultNumber(selection: NumberSelection): StringBuilder {
    val time = StringBuffer("")
    val filledTimeString = StringBuilder().apply {
        when (selection) {
            is NumberSelection.Decimal -> {
                val beforeComma = selection.defaultValue.toString().substringBefore(".")
                val afterComma = selection.defaultValue.toString().substringAfter(".")
                append(beforeComma.padStart(6, '0'))
                append(afterComma.padEnd(selection.decimalPlaces, '0'))
            }

            is NumberSelection.Integer -> {
                val valuePairs = getValuePairs(selection)
                valuePairs.forEach {
                    time.append(it.first)
                }
            }
        }
    }
    return filledTimeString
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun inputValue(
    timeValues: List<String>,
    groupIndex: MutableState<Int>,
    currentIndex: MutableState<Int>,
    newValue: Int
): List<String> {

    val newValues = timeValues.toMutableList()
    when (groupIndex.value) {
        0 -> {
            val number = newValues[0]
            if (number.isEmpty() || number.firstOrNull() == '0' && number.length == 1) {
                newValues[0] = newValue.toString()
            } else {
                newValues[0] = number + newValue.toString()
            }
            currentIndex.value = newValues[0].length
        }

        1 -> {
            val numberFraction = newValues.getOrNull(1)
            val fractionArray = numberFraction?.toCharArray()?.toList()?.toMutableList()
            numberFraction?.get(1)?.let { fractionArray?.set(0, it) }
            fractionArray?.set(1, newValue.toString().first())
            newValues[1] = fractionArray?.toCharArray()?.let { String(it) } ?: ""
            currentIndex.value = newValues[1].length
        }
    }

    return newValues
}


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun moveToPreviousIndex(
    valueIndex: MutableState<Int>,
    groupIndex: MutableState<Int>,
    maxGroupIndex: Int
) {
    val lengthBasedOnGroup: (Int) -> Int = { newGroupIndex ->
        when (newGroupIndex) {
            0 -> 5
            1 -> 1
            else -> 2
        }
    }
    when {
        valueIndex.value > 0 -> valueIndex.value =
            valueIndex.value.minus(1)

        valueIndex.value <= lengthBasedOnGroup(groupIndex.value) && groupIndex.value > 0 -> {
            groupIndex.value = groupIndex.value.minus(1)
            valueIndex.value = lengthBasedOnGroup(groupIndex.value)
        }

        else -> {
            valueIndex.value = 0
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
    val lengthBasedOnGroup = when (groupIndex.value) {
        0 -> 1
        1 -> 1
        else -> 2
    }
    when {
        valueIndex.value < lengthBasedOnGroup -> {
//            valueIndex.value = valueIndex.value.plus(1)
        }

        valueIndex.value >= lengthBasedOnGroup && groupIndex.value < maxGroupIndex -> {
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
internal fun getInputKeys(): List<String> {
    return mutableListOf(
        *(1..9).toList().map { it.toString() }.toTypedArray(),
        Constants.ACTION_PREV,
        "0",
        Constants.ACTION_NEXT
    )
}
