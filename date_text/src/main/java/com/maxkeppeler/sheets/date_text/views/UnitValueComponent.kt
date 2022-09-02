package com.maxkeppeler.sheets.date_text.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_text.models.UnitSelection

@Composable
internal fun ValueContainerComponent(
    unit: UnitSelection,
    width: MutableState<Int>,
    expanded: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .padding(horizontal = 8.dp),
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
