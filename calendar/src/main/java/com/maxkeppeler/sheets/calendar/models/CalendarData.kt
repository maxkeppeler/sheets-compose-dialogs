/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
 * Defines all calculated information for the current view.
 * The current view can be [CalendarStyle.WEEK] or [CalendarStyle.MONTH].
 * @param offsetStart The amount of days before the week or month actually starts.
 * @param weekCameraDate The current camera-date of the week view.
 * @param cameraDate The current camera-date of the month view.
 * @param days The amount of days to be displayed.
 */
internal data class CalendarData(
    val offsetStart: Int,
    val weekCameraDate: LocalDate,
    val cameraDate: LocalDate,
    val days: List<List<Pair<CalendarViewType, Any>>>,
)