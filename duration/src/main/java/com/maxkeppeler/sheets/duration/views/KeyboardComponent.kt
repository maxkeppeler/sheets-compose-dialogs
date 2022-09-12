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

/**
 * The keyboard component that is used to input the clock time.
 * @param keys A list of keys that will be displayed.
 * @param onEnterValue The listener that is invoked when a value was clicked.
 * @param onBackspaceAction The listener that is invoked when [Constants.ACTION_BACKSPACE] was clicked.
 * @param onEmptyAction The listener that is invoked when [Constants.ACTION_CLEAR] was clicked.
 */
@Composable
internal fun KeyboardComponent(
    keys: List<String>,
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
        items(keys) { option ->
            KeyItemComponent(
                key = option,
                onBackspaceAction = onBackspaceAction,
                onEmptyAction = onEmptyAction,
                onEnterValue = onEnterValue
            )
        }
    }
}
