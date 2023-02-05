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
package com.maxkeppeler.sheets.duration.utils

import androidx.annotation.RestrictTo
import androidx.annotation.StringRes
import com.maxkeppeler.sheets.duration.R
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import java.util.concurrent.TimeUnit


/** Splits seconds into days, hours, minutes and seconds. */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun splitTime(timeInSeconds: Long): TimeInfo {

    val days = timeInSeconds / 86400
    val hours = timeInSeconds / (60 * 60) % 24
    val minutes = timeInSeconds / 60 % 60
    val sec = timeInSeconds % 60

    return TimeInfo(
        seconds = sec,
        minutes = minutes,
        hours = hours,
        days = days
    )
}

/** Helper class to store time units. */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
data class TimeInfo(
    val days: Long = 0,
    val hours: Long = 0,
    val minutes: Long = 0,
    val seconds: Long = 0
) {
    override fun toString(): String {
        return "Time(days=$days, hours=$hours, minutes=$minutes, seconds=$seconds)"
    }
}

internal fun parseCurrentTime(format: DurationFormat, currentTime: Long? = null): StringBuilder {

    val time = StringBuffer("")
    val (days, hours, minutes, seconds) = splitTime(currentTime ?: 0)

    // No support for days yet
    val filledTimeString = StringBuilder().apply {
        when (format) {
            DurationFormat.HH_MM_SS -> {
                append(hours.toString().padStart(2, '0'))
                append(minutes.toString().padStart(2, '0'))
                append(seconds.toString().padStart(2, '0'))
            }
            DurationFormat.HH_MM -> {
                append(hours.toString().padStart(2, '0'))
                append(minutes.toString().padStart(2, '0'))
            }
            DurationFormat.MM_SS -> {
                append(minutes.toString().padStart(2, '0'))
                append(seconds.toString().padStart(2, '0'))
            }
            DurationFormat.M_SS -> {
                append(minutes.toString().substring(0, minutes.toString().length.coerceAtMost(1)))
                append(seconds.toString().padStart(2, '0'))
            }
            DurationFormat.HH -> {
                append(hours.toString().padStart(2, '0'))
            }
            DurationFormat.MM -> {
                append(minutes.toString().padStart(2, '0'))
            }
            DurationFormat.SS -> {
                append(seconds.toString().padStart(2, '0'))
            }
        }
    }
    filledTimeString.reversed().forEach {
        time.insert(0, it)
    }

    return filledTimeString
}

data class Label(@StringRes val short: Int, @StringRes val long: Int)

val labels = listOf(
    Label(R.string.scd_duration_dialog_hour_code, R.string.scd_duration_dialog_hours),
    Label(R.string.scd_duration_dialog_minute_code, R.string.scd_duration_dialog_minutes),
    Label(R.string.scd_duration_dialog_second_code, R.string.scd_duration_dialog_seconds)
)

internal fun getValuePairs(
    time: StringBuilder,
    timeConfig: DurationConfig
): List<Pair<String, Label>> {

    val valuePairs = mutableListOf<Pair<String, Label>>()
    val formatArray = timeConfig.timeFormat.name.split("_")
    var indexPosition = 0
    formatArray.forEachIndexed { i, formatTimeUnit ->
        val unitLabel = when {
            formatTimeUnit.contains("H", ignoreCase = true) -> labels[0]
            formatTimeUnit.contains("M", ignoreCase = true) -> labels[1]
            formatTimeUnit.contains("S", ignoreCase = true) -> labels[2]
            else -> throw IllegalStateException("Unit could not be mapped.")
        }
        val value = time.substring(indexPosition, indexPosition.plus(formatTimeUnit.length))
        val valueInt = runCatching { value }.getOrElse { "00" }
        valuePairs.add(valueInt to unitLabel)
        indexPosition += formatTimeUnit.length
    }
    return valuePairs
}

/** Calculates the current time in seconds based on the input. */
internal fun parseToSeconds(time: StringBuilder, format: DurationFormat): Long {

    var timeInSeconds = 0L
    val reversedTime = time.reversed()
    val timeIntoFormat = StringBuilder(reversedTime)
    for (i in 2..timeIntoFormat.length step 3) {
        timeIntoFormat.insert(i, '_')
    }
    val timeReversedArray = timeIntoFormat.split("_")
    val formatReversedArray = format.name.reversed().split("_")
    formatReversedArray.forEachIndexed { i, formatTimeUnit ->
        if (i >= timeReversedArray.size) return@forEachIndexed
        val time = timeReversedArray[i]
        if (time.isEmpty()) return@forEachIndexed
        when {
            formatTimeUnit.contains("H", ignoreCase = true) -> {
                timeInSeconds += TimeUnit.HOURS.toSeconds(time.reversed().toLong())
            }
            formatTimeUnit.contains("M", ignoreCase = true) -> {
                timeInSeconds += TimeUnit.MINUTES.toSeconds(time.reversed().toLong())
            }
            formatTimeUnit.contains("S", ignoreCase = true) -> {
                timeInSeconds += time.reversed().toInt()
            }
        }
    }

    return timeInSeconds
}

internal fun getInputKeys(config: DurationConfig): List<String> {
    return mutableListOf(
        *(1..9).toList().map { it.toString() }.toTypedArray(),
        if (config.displayClearButton) Constants.ACTION_CLEAR else "00",
        "0",
        Constants.ACTION_BACKSPACE
    )
}


internal fun getFormattedHintTime(timeInSeconds: Long): MutableList<Pair<String, Label>> {

    val pairs = mutableListOf<Pair<String, Label>>()

    if (timeInSeconds > 0) {

        var secondsValue = timeInSeconds
        val days = TimeUnit.SECONDS.toDays(secondsValue).toInt()
        secondsValue -= TimeUnit.DAYS.toSeconds(days.toLong())
        val hours = TimeUnit.SECONDS.toHours(secondsValue).toInt()
        secondsValue -= TimeUnit.HOURS.toSeconds(hours.toLong())
        val minutes = TimeUnit.SECONDS.toMinutes(secondsValue).toInt()
        secondsValue -= TimeUnit.MINUTES.toSeconds(minutes.toLong())
        val seconds = TimeUnit.SECONDS.toSeconds(secondsValue).toInt()

        if (hours > 0) pairs.add(Pair(hours.toString(), labels[0]))
        if (minutes > 0) pairs.add(Pair(minutes.toString(),labels[1]))
        if (seconds > 0) pairs.add(Pair(seconds.toString(), labels[2]))

    }

    return pairs
}