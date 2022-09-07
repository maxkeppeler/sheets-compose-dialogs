package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.core.R as RC

/**
 * The value item component that can be selected.
 * @param modifier The modifier that is applied to this component.
 * @param option The option that the current component reflect.
 * @param onValueChange The listener that returns the new selection.
 */
@Composable
internal fun SelectionValueItem(
    modifier: Modifier = Modifier,
    option: UnitOptionEntry?,
    onValueChange: (UnitOptionEntry) -> Unit
) {
    Text(
        maxLines = 1,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { option?.let { onValueChange.invoke(it) } }
            .padding(vertical = dimensionResource(RC.dimen.scd_small_100))
            .padding(horizontal = dimensionResource(RC.dimen.scd_small_100)),
        text = option?.label ?: "",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}