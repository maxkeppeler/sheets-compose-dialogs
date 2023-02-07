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
package com.maxkeppeler.sheets.date_time.models

import androidx.annotation.StringRes
import com.maxkeppeler.sheets.date_time.R

enum class UnitType(val isDate: Boolean) {
    DAY(true),
    MONTH(true),
    YEAR(true),
    HOUR(false),
    MINUTE(false),
    SECOND(false),
    AM_PM(false)
}

/**
 * A representation of a unit with a placeholder, the options that can be selected and the current value.
 * @param placeholderRes The resource of a text that will be used as placeholder text.
 * @param options The list of of options that can be selected.
 * @param value The current selected value.
 */
internal sealed class UnitSelection(
    @StringRes open val placeholderRes: Int? = null,
    open val options: List<UnitOptionEntry> = listOf(),
    open val value: UnitOptionEntry? = null,
    open val type: UnitType? = null
) {

    /**
     * Representation of the selection between am and pm for the 12HourFormat.
     */
    data class AmPm(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        type = UnitType.AM_PM
    )

    /**
     * Representation of the hours selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Hour(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_hour,
        type = UnitType.HOUR
    )

    /**
     * Representation of the minutes selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Minute(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_minutes,
        type = UnitType.MINUTE
    )

    /**
     * Representation of the seconds selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Second(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_seconds,
        type = UnitType.SECOND
    )

    /**
     * Representation of the day selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Day(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_day,
        type = UnitType.DAY
    )

    /**
     * Representation of the month selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Month(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_month,
        type = UnitType.MONTH
    )

    /**
     * Representation of the year selection.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class Year(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_year,
        type = UnitType.YEAR
    )
}