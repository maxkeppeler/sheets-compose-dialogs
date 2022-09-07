package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The container view that builds up the value view. It consists of the label and the value.
 * @param unit The unit of the value.
 * @param width The width of the component.
 * @param expanded If the value-picker is displayed.
 */
@Composable
internal fun ValueContainerComponent(
    unit: UnitSelection,
    width: MutableState<Int>,
    expanded: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .padding(vertical = dimensionResource(RC.dimen.scd_normal_150))
            .padding(horizontal = dimensionResource(RC.dimen.scd_small_100)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitLabel(unit = unit)
        Box {
            ValueComponent(
                unit = unit,
                width = width,
                onClick = { expanded.value = true }
            )
            if (unit.value == null) {
                ValueEmptyOverlayComponent(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
