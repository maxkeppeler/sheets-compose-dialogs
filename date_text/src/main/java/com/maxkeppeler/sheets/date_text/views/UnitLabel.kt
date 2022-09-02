package com.maxkeppeler.sheets.date_text.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_text.models.UnitSelection

@Composable
internal fun UnitLabel(unit: UnitSelection) {
    Text(
        modifier = Modifier.padding(bottom = 12.dp),
        text = unit.placeholderRes?.let { stringResource(id = it) } ?: "",
        style = MaterialTheme.typography.labelMedium
    )
}