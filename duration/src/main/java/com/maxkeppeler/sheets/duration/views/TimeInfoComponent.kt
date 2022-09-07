package com.maxkeppeler.sheets.duration.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.duration.utils.getFormattedHintTime
import com.maxkeppeler.sheets.duration.R

/**
 * The info component that will show a hint if the selected time is out of the specified bounds.
 * @param minTimeValue The minimum valid time.
 * @param maxTimeValue The maximum valid time.
 */
@Composable
internal fun TimeInfoComponent(
    minTimeValue: Long? = null,
    maxTimeValue: Long? = null,
) {

    if (minTimeValue != null || maxTimeValue != null) {

        val labelRes =
            if (minTimeValue != null) R.string.sheets_at_least_placeholder
            else if (maxTimeValue != null) R.string.sheets_at_most_placeholder
            else null

        val options = getFormattedHintTime(minTimeValue ?: maxTimeValue!!)
        labelRes?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = stringResource(id = labelRes))
                TimeValueComponent(
                    valuePairs = options,
                    hintView = true
                )
            }
        }
    }
}
