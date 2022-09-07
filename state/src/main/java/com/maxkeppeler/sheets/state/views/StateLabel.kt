package com.maxkeppeler.sheets.state.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.models.State

@Composable
internal fun StateLabel(state: State) {
    state.labelText?.let { label ->
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}