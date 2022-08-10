package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

internal fun LazyListScope.setupYearSelectionView(
    yearsRange: IntRange,
    selectedYear: Int,
    onYearClick: (Int) -> Unit
) {
    item { Spacer(modifier = Modifier.width(48.dp)) }
    items(yearsRange.last.minus(yearsRange.first)) {
        val year = yearsRange.first + it
        val selected = selectedYear == year
        val thisYear = year == LocalDate.now().year
        CalendarViewMainYearComponent(
            year = year,
            thisYear = thisYear,
            selected = selected,
            onYearClick = onYearClick
        )
    }
    item { Spacer(modifier = Modifier.width(48.dp)) }
}