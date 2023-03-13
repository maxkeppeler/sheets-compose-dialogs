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
package com.maxkeppeler.sheets.calendar.utils

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

/**
 * Returns a map of day of week labels in the specified locale.
 *
 * @param locale the locale to get the labels for
 * @return a map of day of week labels
 */
internal fun getDayOfWeekLabels(locale: Locale): Map<DayOfWeek, String> = when {
    Locale.SIMPLIFIED_CHINESE.let { locale.language == it.language && locale.country == it.country } -> getSimplifiedChineseDayOfWeekLabels()
    Locale.JAPANESE.let { locale.language == it.language } -> getJapaneseDayOfWeekLabels()
    else -> getDefaultDayOfWeekLabels(locale)
}

/**
 * Returns a map of day of week labels in the default locale.
 *
 * @param locale the locale to get the labels for
 * @return a map of day of week labels
 */
private fun getDefaultDayOfWeekLabels(locale: Locale): Map<DayOfWeek, String> =
    DayOfWeek.values()
        .associateWith { dayOfWeek -> dayOfWeek.getDisplayName(TextStyle.NARROW, locale) }

/**
 * Returns a map of day of week labels in Simplified Chinese locale.
 *
 * @return a map of day of week labels
 */
private fun getSimplifiedChineseDayOfWeekLabels(): Map<DayOfWeek, String> = mapOf(
    DayOfWeek.MONDAY to "\u4e00",
    DayOfWeek.TUESDAY to "\u4e8c",
    DayOfWeek.WEDNESDAY to "\u4e09",
    DayOfWeek.THURSDAY to "\u56db",
    DayOfWeek.FRIDAY to "\u4e94",
    DayOfWeek.SATURDAY to "\u516d",
    DayOfWeek.SUNDAY to "\u65e5",
)

/**
 * Returns a map of day of week labels in Japanese locale.
 *
 * @return a map of day of week labels
 */
private fun getJapaneseDayOfWeekLabels(): Map<DayOfWeek, String> = mapOf(
    DayOfWeek.MONDAY to "\u6708",
    DayOfWeek.TUESDAY to "\u706b",
    DayOfWeek.WEDNESDAY to "\u6c34",
    DayOfWeek.THURSDAY to "\u6728",
    DayOfWeek.FRIDAY to "\u91d1",
    DayOfWeek.SATURDAY to "\u571f",
    DayOfWeek.SUNDAY to "\u65e5",
)