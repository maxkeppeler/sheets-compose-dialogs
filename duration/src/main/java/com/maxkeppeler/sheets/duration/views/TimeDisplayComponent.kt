package com.maxkeppeler.sheets.duration.views

import androidx.compose.runtime.Composable

/**
 * The main component that includes the time view and an info view,
 * when the selected time is out of the bounds of the configuration.
 * @param indexOfFirstValue The index of the first valid value.
 * @param valuePairs The list of value pairs that will be displayed.
 * @param minTimeValue The minimum valid time.
 * @param maxTimeValue The maximum valid time.
 */
@Composable
internal fun TimeDisplayComponent(
    indexOfFirstValue: Int?,
    valuePairs: List<Pair<String, String>>,
    minTimeValue: Long?,
    maxTimeValue: Long?
) {

    TimeValueComponent(
        indexOfFirstValue = indexOfFirstValue,
        valuePairs = valuePairs
    )

    TimeInfoComponent(
        minTimeValue = minTimeValue,
        maxTimeValue = maxTimeValue
    )
}



