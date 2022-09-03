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

@Composable
internal fun TimeInfoComponent(
    minValue: Long? = null,
    maxValue: Long? = null,
) {

    if (minValue != null || maxValue != null) {

        val labelRes =
            if (minValue != null) com.maxkeppeler.sheets.duration.R.string.sheets_at_least_placeholder
            else if (maxValue != null) com.maxkeppeler.sheets.duration.R.string.sheets_at_most_placeholder
            else null

        val options = getFormattedHintTime(minValue ?: maxValue!!)
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
