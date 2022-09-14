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
package com.mk.sheets.compose

import java.io.Serializable


enum class UseCase(val category: UseCaseCategory, vararg val specifics: String) : Serializable {

    CORE_SAMPLE_1(
        UseCaseCategory.CORE,
        "Standard"
    ),

    INFO_SAMPLE_1(
        UseCaseCategory.INFO,
        "Standard"
    ),

    COLOR_SAMPLE_1(
        UseCaseCategory.COLOR,
        "With template colors",
        "With custom color",
        "With alpha values",
        "With no-color selection",
    ),

    COLOR_SAMPLE_2(
        UseCaseCategory.COLOR,
        "Default view custom color",
        "Default color",
        "Don’t allow colors with alpha",
    ),

    COLOR_SAMPLE_3(
        UseCaseCategory.COLOR,
        "Only custom color view",
        "Allow colors with alpha",
    ),

    CALENDAR_SAMPLE_1(
        UseCaseCategory.CALENDAR,
        "Select multiple dates",
        "Month-style",
        "Allow month selection",
        "Allow year selection",
        "Disabled dates"
    ),

    CALENDAR_SAMPLE_2(
        UseCaseCategory.CALENDAR,
        "Select date",
        "Default selected date",
        "week-style",
        "Allow year selection",
        "Disabled dates"
    ),

    CALENDAR_SAMPLE_3(
        UseCaseCategory.CALENDAR,
        "Select period",
        "Default selected period",
        "Month-style",
        "Disabled past timeline"
    ),

    CLOCK_SAMPLE_1(
        UseCaseCategory.CLOCK,
        "Hours and minutes selection",
        "24HourFormat",
        "Default time"
    ),

    CLOCK_SAMPLE_2(
        UseCaseCategory.CLOCK,
        "Hours, minutes and seconds selection",
        "12HourFormat",
    ),


    DATE_TIME_SAMPLE_1(
        UseCaseCategory.DATE_TIME,
        "Select date and time",
        "Start with time selection first"
    ),

    DATE_TIME_SAMPLE_2(
        UseCaseCategory.DATE_TIME,
        "Select only date",
    ),

    DATE_TIME_SAMPLE_3(
        UseCaseCategory.DATE_TIME,
        "Select only time",
    ),

    DURATION_SAMPLE_1(
        UseCaseCategory.DURATION,
        "HH_MM_SS Format time selection",
        "Default time",
    ),

    DURATION_SAMPLE_2(
        UseCaseCategory.DURATION,
        "MM_SS Format time selection",
        "Min & max time",
    ),

    OPTION_SAMPLE_1(
        UseCaseCategory.OPTION,
        "Single selection",
        "Display mode list",
        "Details information for one option",
        "Default option selection",
        "1 option is disabled"
    ),

    OPTION_SAMPLE_2(
        UseCaseCategory.OPTION,
        "Single selection",
        "Display mode horizontal grid",
        "Default option selection",
        "1 option is disabled"
    ),

    OPTION_SAMPLE_3(
        UseCaseCategory.OPTION,
        "Multiple selection",
        "Display mode vertical grid",
        "Default option selection",
        "Details information for one option",
        "Min & max amount selection"
    ),

    LIST_SAMPLE_1(
        UseCaseCategory.LIST,
        "Single selection",
        "With radio buttons",
        "Default option selection",
    ),

    LIST_SAMPLE_2(
        UseCaseCategory.LIST,
        "Multiple selection",
        "With checkbox buttons",
        "Default option selection",
    ),

    LIST_SAMPLE_3(
        UseCaseCategory.LIST,
        "Multiple selection",
        "Min & max amount of choices",
    ),

    LIST_SAMPLE_4(
        UseCaseCategory.LIST,
        "Single selection",
        "Simple list",
    ),

    EMOJI_SAMPLE_1(
        UseCaseCategory.EMOJI,
        "Android rendering",
        "Unicode selection",
        "Categories as symbols"
    ),

    EMOJI_SAMPLE_2(
        UseCaseCategory.EMOJI,
        "iOS rendering",
        "Unicode selection",
        "Categories as text"
    ),


    STATE_SAMPLE_1(
        UseCaseCategory.STATE,
        "Skip between states",
        "1. Loading\n2. Failure\n3. Loading\n4. Success",
    ),

    STATE_SAMPLE_2(
        UseCaseCategory.STATE,
        "Loading state",
        "Determinate horizontal",
    ),

    STATE_SAMPLE_3(
        UseCaseCategory.STATE,
        "Loading state",
        "Determinate circular",
    ),

    STATE_SAMPLE_4(
        UseCaseCategory.STATE,
        "Loading state",
        "Indeterminate horizontal",
    ),

    STATE_SAMPLE_5(
        UseCaseCategory.STATE,
        "Loading state",
        "Indeterminate circular",
    ),

    STATE_SAMPLE_6(
        UseCaseCategory.STATE,
        "Failure state",
    ),


    STATE_SAMPLE_7(
        UseCaseCategory.STATE,
        "Success state",
    ),

}