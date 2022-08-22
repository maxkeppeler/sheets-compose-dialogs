package com.maxkeppeler.sheets.clock_time.views


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun TimeInputComponent(
    keys: List<String>,
    disabledKeys: List<String>,
    onEnterValue: (Int) -> Unit,
    onPrevAction: () -> Unit,
    onNextAction: () -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        columns = GridCells.Fixed(3),
        userScrollEnabled = false
    ) {
        items(keys) { key ->
            val disabled = disabledKeys.contains(key)
            InputItemComponent(
                key = key,
                disabled = disabled,
                onNextAction = onNextAction,
                onPrevAction = onPrevAction,
                onEnterValue = onEnterValue
            )
        }
    }
}
