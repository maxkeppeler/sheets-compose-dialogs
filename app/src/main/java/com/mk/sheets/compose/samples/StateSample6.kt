@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig

@Composable
internal fun StateSample6(closeSelection: () -> Unit) {

    val state = State.Failure(
        labelText = "The upload of your photos failed.",
    )

    StateDialog(
        show = true,
        config = StateConfig(state = state),
        onClose = { closeSelection() }
    )
}