package com.maxkeppeler.sheets.duration.views


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
    options: List<String>,
    onEnterValue: (String) -> Unit,
    onBackspaceAction: () -> Unit,
    onEmptyAction: () -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        columns = GridCells.Fixed(3),
        userScrollEnabled = false
    ) {
        items(options) { option ->
            InputItemComponent(
                option = option,
                onBackspaceAction = onBackspaceAction,
                onEmptyAction = onEmptyAction,
                onEnterValue = onEnterValue
            )
        }
    }
}
