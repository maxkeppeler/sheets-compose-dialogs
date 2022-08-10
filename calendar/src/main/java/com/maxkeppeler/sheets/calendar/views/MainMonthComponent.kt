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
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
internal fun CalendarViewMonthComponent(
    month: Month? = null,
    thisMonth: Boolean = false,
    selected: Boolean = false,
    onMonthClick: ((Month) -> Unit)? = null
) {
    val textStyle =
        when {
            selected -> MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onPrimary)
            thisMonth -> MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.primary)
            else -> MaterialTheme.typography.bodyMedium
        }

    val baseModifier = Modifier
        .wrapContentWidth()
        .padding(4.dp)

    val normalModifier = baseModifier
        .clip(MaterialTheme.shapes.small)
        .clickable { onMonthClick?.invoke(month!!) }

    val selectedModifier = normalModifier
        .background(MaterialTheme.colorScheme.primary)

    Column(
        modifier = when {
            month == null -> baseModifier
            selected -> selectedModifier
            else -> normalModifier
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(vertical = 8.dp),
            text = month?.let {
                LocalDate.now().withMonth(month.value)
                    .format(DateTimeFormatter.ofPattern("MMM"))
            } ?: "",
            style = textStyle,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}