package com.maxkeppeler.sheets.date_time.models

import androidx.annotation.StringRes
import com.maxkeppeler.sheets.date_time.R

internal sealed class UnitSelection(
    @StringRes open val placeholderRes: Int? = null,
    open val options: List<UnitOptionEntry> = listOf(),
    open val value: UnitOptionEntry? = null
) {

    data class AmPm(
        override val options: List<UnitOptionEntry> = listOf(
            UnitOptionEntry(0, labelRes = R.string.sheets_compose_dialogs_date_time_am),
            UnitOptionEntry(1, labelRes = R.string.sheets_compose_dialogs_date_time_pm),
        ),
        override val value: UnitOptionEntry? = options.first(),
    ) : UnitSelection()

    data class Hour(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_hour,
    )

    data class Minute(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_minutes,
    )

    data class Second(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_seconds,
    )

    data class Day(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_day,
    )

    data class Month(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>,
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_month,
    )

    data class Year(
        override val value: UnitOptionEntry? = null,
        override val options: List<UnitOptionEntry>
    ) : UnitSelection(
        placeholderRes = R.string.sheets_compose_dialogs_date_time_year,
    )
}