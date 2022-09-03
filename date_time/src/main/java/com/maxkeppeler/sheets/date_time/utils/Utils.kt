package com.maxkeppeler.sheets.date_time.utils

import com.maxkeppeler.sheets.date_time.models.DateTextConfig
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.chrono.Chronology
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.util.*


internal fun detectUnit(
    config: DateTextConfig,
    pattern: String,
    segment: String,
    sec: UnitOptionEntry?,
    min: UnitOptionEntry?,
    hour: UnitOptionEntry?,
    day: UnitOptionEntry?,
    month: UnitOptionEntry?,
    year: UnitOptionEntry?,
): UnitSelection? {
    val now = LocalDate.now()
    val date = LocalDate.of(year?.value ?: now.year, month?.value ?: now.monthValue, 1)
    return when {
        segment.contains(Constants.SYMBOL_SECONDS) ->
            UnitSelection.Second(
                value = sec,
                options = getMinutesSecondsOptions()
            )
        segment.contains(Constants.SYMBOL_MINUTES) ->
            UnitSelection.Minute(
                value = min,
                options = getMinutesSecondsOptions()
            )
        segment.contains(Constants.SYMBOL_HOUR, ignoreCase = true) ->
            UnitSelection.Hour(
                value = hour,
                options = getHoursOptions(pattern)
            )
        segment.contains(Constants.SYMBOL_DAY) ->
            UnitSelection.Day(
                value = day,
                options = getDayOptions(date, month)
            )
        segment.contains(Constants.SYMBOL_MONTH) ->
            UnitSelection.Month(
                value = month,
                options = getMonthOptions(segment)
            )
        segment.contains(Constants.SYMBOL_YEAR, ignoreCase = true) ->
            UnitSelection.Year(
                value = year,
                options = getYearOptions(config)
            )

        else -> null
    }
}

internal fun getLocalTimeOf(
    isAm: Boolean?,
    sec: UnitOptionEntry?,
    min: UnitOptionEntry?,
    hour: UnitOptionEntry?
) = runCatching {

    val hourValue = hour!!.value
    val minValue = min!!.value
    val secValue = sec?.value ?: 0
    var actualHourValue = hourValue

    isAm?.let {
        if (isAm && actualHourValue >= 12 && minValue > 0) actualHourValue -= 12
        else if (!isAm && ((actualHourValue < 12 && minValue >= 0) || (actualHourValue == 12 && minValue == 0))) actualHourValue += 12
        if (actualHourValue == 24) actualHourValue = 0
    }

    LocalTime.of(
        actualHourValue,
        minValue,
        secValue
    )

}.getOrNull()

internal fun getLocalDateOf(
    day: UnitOptionEntry?,
    month: UnitOptionEntry?,
    year: UnitOptionEntry?
) = runCatching {
    LocalDate.of(
        year!!.value,
        month!!.value,
        day!!.value
    )
}.getOrNull()

internal fun getLocalizedPattern(
    isDate: Boolean,
    formatStyle: FormatStyle,
    chronology: Chronology,
    locale: Locale
): String = DateTimeFormatterBuilder.getLocalizedDateTimePattern(
    if (isDate) formatStyle else null,
    if (!isDate) formatStyle else null, chronology, locale
).toString()

internal fun getLocalizedValues(pattern: String): Array<String> =
    pattern.split(" ", ".", ":").filter { !containsAmPm(it) }.toTypedArray()

internal fun getLocalizedValueSegments(segment: String): List<String> =
    segment.split(",", ".").dropLastWhile { it.isEmpty() }

internal fun is24HourFormat(pattern: String): Boolean = !containsAmPm(pattern)

private fun containsAmPm(pattern: String): Boolean =
    pattern.contains(Constants.SYMBOL_24_HOUR_FORMAT)

internal fun containsSeconds(pattern: String): Boolean = pattern.contains(Constants.SYMBOL_SECONDS)

private fun getMinutesSecondsOptions(): List<UnitOptionEntry> {
    return (0..59).map {
        UnitOptionEntry(
            it,
            it.toString().padStart(2, '0')
        )
    }.toList()
}

private fun getHoursOptions(pattern: String): List<UnitOptionEntry> =
    if (is24HourFormat(pattern)) {
        (0..23).map { value ->
            UnitOptionEntry(
                value = value,
                label = value.toString().padStart(2, '0')
            )
        }.toList()
    } else {
        (1..12).map { value ->
            UnitOptionEntry(
                value = value,
                label = value.toString()
            )
        }.toList()
    }

private fun getDayOptions(date: LocalDate, month: UnitOptionEntry?): List<UnitOptionEntry> {
    val daysInMonth = date.lengthOfMonth()
    return (1..(if (month?.value != null) 31 else daysInMonth)).map {
        UnitOptionEntry(
            it,
            it.toString()
        )
    }
}

private fun getMonthOptions(pattern: String): List<UnitOptionEntry> {
    val occurrences = pattern.count { it.equals('m', ignoreCase = true) }
    return when {
        occurrences >= 3 -> {
            Month.values().map { month ->
                UnitOptionEntry(
                    month.value, LocalDate.now().withMonth(month.value)
                        .format(DateTimeFormatter.ofPattern(pattern))
                )
            }
        }
        else -> Month.values().map { it.value }.map {
            UnitOptionEntry(it, it.toString())
        }.toList()
    }
}

private fun getYearOptions(config: DateTextConfig): List<UnitOptionEntry> =
    IntRange(config.minYear, config.maxYear.plus(1)).map { value ->
        UnitOptionEntry(
            value = value,
            label = value.toString()
        )
    }.reversed()