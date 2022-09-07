package com.maxkeppeler.sheets.duration.views


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.duration.utils.Constants
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun KeyboardComponent(
    options: List<String>,
    onEnterValue: (String) -> Unit,
    onBackspaceAction: () -> Unit,
    onEmptyAction: () -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(RC.dimen.scd_normal_150),
                vertical = dimensionResource(RC.dimen.scd_normal_100)
            ),
        columns = GridCells.Fixed(Constants.KEYBOARD_COLUMNS),
        userScrollEnabled = false
    ) {
        items(options) { option ->
            KeyItemComponent(
                option = option,
                onBackspaceAction = onBackspaceAction,
                onEmptyAction = onEmptyAction,
                onEnterValue = onEnterValue
            )
        }
    }
}
