package com.maxkeppeler.sheets.date_text.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun PickerDateCharacterComponent(text: String) {
    Text(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .padding(start = 2.dp)
            .padding(bottom = 24.dp)
            .padding(end = 8.dp),
        text = text
    )
}
