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
package com.maxkeppeler.sheets.calendar.models

import java.time.LocalDate

/**
 * Defines all calculated information for a specific date.
 * @param date The current date that the view represents.
 * @param disabled Whenever the date is disabled.
 * @param selected Whenever the date is selected in a single or multiple date selection.
 * @param selectedBetween Whenever the date is within a range selection.
 * @param selectedStart Whenever the date is the start of a range selection.
 * @param selectedEnd Whenever the date is the end of a range selection.
 * @param otherMonth Whenever the date lays in another month.
 */
internal data class CalendarDateData(
    val date: LocalDate? = null,
    val disabled: Boolean = false,
    val selected: Boolean = false,
    val selectedBetween: Boolean = false,
    val selectedStart: Boolean = false,
    val selectedEnd: Boolean = false,
    val otherMonth: Boolean = false,
)