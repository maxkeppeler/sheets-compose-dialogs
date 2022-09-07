package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The label that is displayed above the value unit.
 * @param unit The value unit
 */
@Composable
internal fun UnitLabel(unit: UnitSelection) {
    Text(
        modifier = Modifier.padding(bottom = dimensionResource(RC.dimen.scd_small_150)),
        text = unit.placeholderRes?.let { stringResource(id = it) } ?: "",
        style = MaterialTheme.typography.labelMedium
    )
}