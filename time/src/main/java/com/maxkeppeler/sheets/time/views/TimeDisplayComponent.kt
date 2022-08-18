package com.maxkeppeler.sheets.time.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
internal fun TimeDisplayComponent(
    indexOfFirstValue: Int?,
    valuePairs: MutableState<List<Pair<String, String>>>,
    minTimeValue: Long?,
    maxTimeValue: Long?
) {

    TimeValueComponent(
        indexOfFirstValue = indexOfFirstValue,
        valuePairs = valuePairs.value
    )

    TimeInfoComponent(
        minValue = minTimeValue,
        maxValue = maxTimeValue
    )
}



