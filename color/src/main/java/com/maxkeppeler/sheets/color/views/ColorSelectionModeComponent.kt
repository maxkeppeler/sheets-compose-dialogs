package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.NotInterested
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun ColorSelectionModeComponent(
    selection: ColorSelection,
    config: ColorConfig,
    mode: ColorSelectionMode,
    onModeChange: (ColorSelectionMode) -> Unit,
    onNoColorClick: () -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        if (config.displayMode == null) {

            TextButton(
                onClick = {
                    onModeChange(
                        if (mode == ColorSelectionMode.TEMPLATE) ColorSelectionMode.CUSTOM
                        else ColorSelectionMode.TEMPLATE
                    )
                },
                modifier = Modifier,
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = RC.dimen.small_100),
                    horizontal = dimensionResource(id = RC.dimen.small_100)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                    imageVector = if (mode != ColorSelectionMode.TEMPLATE) Icons.Rounded.Apps else Icons.Rounded.Tune,
                    contentDescription = "Select month",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = when (mode) {
                        ColorSelectionMode.CUSTOM -> "Template colors"
                        ColorSelectionMode.TEMPLATE -> "Custom color"
                    },
                )
            }
        }

        if (selection.onSelectNone != null) {
            TextButton(
                onClick = onNoColorClick,
                modifier = Modifier,
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = RC.dimen.small_100),
                    horizontal = dimensionResource(id = RC.dimen.small_100)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                    imageVector = Icons.Rounded.NotInterested,
                    contentDescription = "No color",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "No color",
                )
            }
        }
    }
}
