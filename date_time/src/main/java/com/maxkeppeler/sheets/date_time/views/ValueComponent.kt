package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The value component that is displayed if a selection was made.
 * @param unit The unit of the value.
 * @param width The width of the component.
 * @param onClick The listener that is invoked if this component was selected.
 */
@Composable
internal fun ValueComponent(
    unit: UnitSelection,
    width: MutableState<Int>,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                if (width.value < coordinates.size.width) {
                    width.value = coordinates.size.width
                }
            }
            .clip(MaterialTheme.shapes.small)
            .background(
                if (unit.value != null) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
            .clickable { onClick() }
            .padding(dimensionResource(RC.dimen.scd_normal_100)),
        text = unit.value?.label
            ?: unit.value?.labelRes?.let { stringResource(id = it) }
            ?: unit.options.last().label?.map { "  " }?.joinToString(separator = "")
            ?: unit.options.last().labelRes?.let { stringResource(id = it) }
                ?.map { "  " }?.joinToString(separator = "")!!,
        style = MaterialTheme.typography.bodyLarge
    )
}