package com.maxkeppeler.sheets.date_time.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Component that is applied above the value component if no selection was made.
 * @param modifier The modifier that is applied to this component.
 */
@Composable
internal fun ValueEmptyOverlayComponent(modifier: Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.ExpandMore,
        contentDescription = null
    )
}