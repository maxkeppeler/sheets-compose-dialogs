package com.maxkeppeler.sheets.date_time.models

import androidx.annotation.StringRes

internal data class UnitOptionEntry(
    val value: Int,
    val label: String? = null,
    @StringRes val labelRes: Int? = null
)
