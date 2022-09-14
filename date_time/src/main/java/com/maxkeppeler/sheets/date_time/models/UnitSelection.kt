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

/**
 * A representation of a unit with a placeholder, the options that can be selected and the current value.
 * @param placeholderRes The resource of a text that will be used as placeholder text.
 * @param options The list of of options that can be selected.
 * @param value The current selected value.
 */
internal sealed class UnitSelection(
    @StringRes open val placeholderRes: Int? = null,
    open val options: List<UnitOptionEntry> = listOf(),
    open val value: UnitOptionEntry? = null
) {

    /**
     * Representation of the selection between am and pm for the 12HourFormat.
     * @param options The list of of options that can be selected.
     * @param value The current selected value.
     */
    data class AmPm(
        override val options: List<UnitOptionEntry> = listOf(
            UnitOptionEntry(value = 0, labelRes = R.string.sheets_compose_dialogs_date_time_am),
            UnitOptionEntry(value = 1, labelRes = R.string.sheets_compose_dialogs_date_time_pm),
        ),
        override val value: UnitOptionEntry? = options.first(),
    ) : UnitSelection()

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
    )
}