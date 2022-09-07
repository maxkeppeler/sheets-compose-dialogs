package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC


/**
 * The textual header item component.
 * @param name The name of the category.
 * @param selected If the current category is selected.
 * @param onClick The listener that is invoked when this component is clicked.
 */
@Composable
internal fun EmojiTextHeaderItemComponent(
    name: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .padding(top = dimensionResource(RC.dimen.scd_normal_100))
            .clip(RoundedCornerShape(50))
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable { onClick() }
            .padding(
                horizontal = dimensionResource(RC.dimen.scd_small_150),
                vertical = dimensionResource(RC.dimen.scd_small_50)
            ),
        text = name,
        style = if (selected) MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
        else MaterialTheme.typography.labelLarge
    )
}