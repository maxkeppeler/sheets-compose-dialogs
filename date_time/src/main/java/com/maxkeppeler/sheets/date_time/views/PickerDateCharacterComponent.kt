package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC

/**
 * A component that displays a character of the date pattern.
 * @param text The character that was found in the date pattern.
 */
@Composable
internal fun PickerDateCharacterComponent(text: String) {
    Text(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .padding(start = dimensionResource(RC.dimen.scd_small_25))
            .padding(bottom = dimensionResource(RC.dimen.scd_normal_150))
            .padding(end = dimensionResource(RC.dimen.scd_small_100)),
        text = text
    )
}
