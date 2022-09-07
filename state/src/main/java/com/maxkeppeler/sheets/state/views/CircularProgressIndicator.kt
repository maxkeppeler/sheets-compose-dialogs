package com.maxkeppeler.sheets.state.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.state.R
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun CircularProgressIndicator(
    indicator: ProgressIndicator.Circular,
) {
    val circularIndicatorModifier = Modifier
        .padding(dimensionResource(id = RC.dimen.scd_normal_150))
        .width(dimensionResource(id = R.dimen.scd_state_dialog_indicator_width))
        .aspectRatio(1f)

    indicator.customProgressIndicator?.invoke(indicator.value!!)
        ?: indicator.customIndicator?.invoke()
        ?: indicator.value?.let { progress ->
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = circularIndicatorModifier.align(Alignment.Center)
                )
                if (indicator.showProgressPercentage) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "${progress.times(100)} %",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        } ?: run {
            CircularProgressIndicator(
                modifier = circularIndicatorModifier
            )
        }
}