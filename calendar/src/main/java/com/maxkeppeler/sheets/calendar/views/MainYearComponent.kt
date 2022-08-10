package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
internal fun CalendarViewMainYearComponent(
    year: Int,
    thisYear: Boolean,
    selected: Boolean,
    onYearClick: (Int) -> Unit
) {
    val textStyle =
        when {
            selected -> MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onPrimary)
            thisYear -> MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.primary)
            else -> MaterialTheme.typography.bodyMedium
        }

    val baseModifier = Modifier
        .wrapContentWidth()
        .clip(MaterialTheme.shapes.small)
        .clickable { onYearClick(year) }

    val selectedModifier = baseModifier
        .background(MaterialTheme.colorScheme.primary)
        .clickable { onYearClick(year) }

    Column(
        modifier = if (selected) selectedModifier else baseModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(vertical = 8.dp),
            text = year.toString(),
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}