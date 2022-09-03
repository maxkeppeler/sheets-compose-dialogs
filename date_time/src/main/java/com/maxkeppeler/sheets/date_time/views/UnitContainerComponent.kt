package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection


@Composable
internal fun UnitContainerComponent(
    unit: UnitSelection,
    height: MutableState<Int>,
    onValueChange: (UnitOptionEntry) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val width = remember { mutableStateOf(0) }

    Row(verticalAlignment = Alignment.CenterVertically) {

        if (expanded.value) {
            SelectionContainerComponent(
                heightOffsetTopPadding = 24.dp,
                unit = unit,
                height = height,
                width = width,
                options = unit.options,
                onValueChange = {
                    onValueChange(it)
                    expanded.value = false
                })
        } else {
            ValueContainerComponent(
                unit = unit,
                width = width,
                expanded = expanded
            )
        }
    }
}