package com.maxkeppeler.sheets.clock.views
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC


/**
 * Time type item component that represents either am or pm.
 * @param modifier The modifier that is applied to this component.
 * @param selected If the current item is selected.
 * @param text The text that is displayed.
 * @param onClick The listener that is invoked when this item is clicked.
 */
@Composable
internal fun TimeTypeItemComponent(
    modifier: Modifier,
    selected: Boolean,
    text: String,
    onClick: () -> Unit
) {

    val backgroundColor = if (selected) MaterialTheme.colorScheme.secondaryContainer
    else Color.Transparent

    val textStyle =
        if (selected) MaterialTheme.typography.labelSmall
            .copy(color = MaterialTheme.colorScheme.onSecondaryContainer)
        else MaterialTheme.typography.labelMedium
            .copy(color = MaterialTheme.colorScheme.onSurface)

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .clickable { onClick.invoke() }
            .padding(dimensionResource(RC.dimen.scd_small_100))
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}