package com.maxkeppeler.sheets.date_text.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_text.models.UnitOptionEntry


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
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp),
        text = option?.label ?: "",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}