@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig

@Composable
internal fun StateSample5(closeSelection: () -> Unit) {

    val state = State.Loading(
        "Wait a moment",
        ProgressIndicator.Circular()
    )
    StateDialog(
        show = true,
        config = StateConfig(state = state),
        onClose = { closeSelection() }
    )
}