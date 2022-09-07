package com.maxkeppeler.sheets.state.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun LinearProgressIndicator(
    indicator: ProgressIndicator.Linear,
) {

    val linearIndicatorModifier = Modifier
        .padding(dimensionResource(id = RC.dimen.scd_normal_150))

    indicator.customProgressIndicator?.invoke(indicator.value!!)
        ?: indicator.customIndicator?.invoke()
        ?: indicator.value?.let { progress ->
            if (indicator.showProgressPercentage) {
                Text(
                    text = "${progress.times(100)} %",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = linearIndicatorModifier
            )
        } ?: run {
            LinearProgressIndicator(
                modifier = linearIndicatorModifier
            )
        }
}