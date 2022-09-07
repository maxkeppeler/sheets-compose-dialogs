package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC

/**
 * The emoji header item component.
 * @param imageVector The symbol of the category.
 * @param selected If the current category is selected.
 * @param onClick The listener that is invoked when this component is clicked.
 */
@Composable
internal fun EmojiHeaderItemComponent(
    imageVector: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(RC.dimen.scd_small_25))
            .aspectRatio(1f, true)
            .clip(if (selected) RoundedCornerShape(50) else MaterialTheme.shapes.small)
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable { onClick() }
            .padding(dimensionResource(RC.dimen.scd_small_50)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(RC.dimen.scd_normal_150)),
            imageVector = imageVector,
            contentDescription = "",
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}