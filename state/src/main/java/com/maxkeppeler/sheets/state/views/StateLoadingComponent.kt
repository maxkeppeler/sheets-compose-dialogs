package com.maxkeppeler.sheets.state.views

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State

@Composable
internal fun StateLoadingComponent(state: State.Loading) {
    when (val indicator = state.indicator) {
        is ProgressIndicator.Circular -> CircularProgressIndicator(indicator)
        is ProgressIndicator.Linear -> LinearProgressIndicator(indicator)
        else -> state.customView?.invoke()
    }
}