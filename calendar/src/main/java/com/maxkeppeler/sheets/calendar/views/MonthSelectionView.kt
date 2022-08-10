package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.lazy.grid.LazyGridScope
import java.time.LocalDate
import java.time.Month

internal fun LazyGridScope.setupMonthSelectionView(
    monthRange: IntRange,
    selectedMonth: Month,
    onMonthClick: (Month) -> Unit,
) {
    items(monthRange.first) { CalendarViewMonthComponent() }
    items(monthRange.last.minus(monthRange.first)) { monthIndex ->
        val month = Month.values()[monthIndex.plus(monthRange.first)]
        val selected = selectedMonth == month
        val thisMonth = month == LocalDate.now().month
        CalendarViewMonthComponent(
            month = month,
            thisMonth = thisMonth,
            selected = selected,
            onMonthClick = onMonthClick
        )
    }
    items(12 - monthRange.last) { CalendarViewMonthComponent() }
}

