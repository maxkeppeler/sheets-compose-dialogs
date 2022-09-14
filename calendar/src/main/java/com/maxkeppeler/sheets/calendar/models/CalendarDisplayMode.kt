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

/**
 * Defined display modes for the calendar.
 */
internal enum class CalendarDisplayMode {

    /**
     * The default calendar view.
     */
    CALENDAR,

    /**
     * The month selection view, if [CalendarConfig.monthSelection] is enabled.
     */
    MONTH,

    /**
     * The year selection view, if [CalendarConfig.yearSelection] is enabled.
     */
    YEAR,
}