package com.maxkeppeler.sheets.date_text.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ValueEmptyOverlayComponent(modifier: Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.ExpandMore,
        contentDescription = null
    )
}