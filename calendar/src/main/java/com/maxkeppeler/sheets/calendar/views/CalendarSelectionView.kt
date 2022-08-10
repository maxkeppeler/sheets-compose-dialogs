package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.calendar.models.*
import com.maxkeppeler.sheets.calendar.models.CalendarData
import com.maxkeppeler.sheets.calendar.models.CalendarDateData
import com.maxkeppeler.sheets.calendar.utils.calcCalendarDateData
import java.time.DayOfWeek
import java.time.LocalDate

internal fun LazyGridScope.setupCalendarSelectionView(
    cells: Int,
    config: CalendarConfig,
    data: CalendarData,
    today: LocalDate,
    selection: CalendarSelection,
    onSelect: (LocalDate) -> Unit,
    selectedDate: LocalDate?,
    selectedDates: List<LocalDate>?,
    selectedRange: Pair<LocalDate?, LocalDate?>
) {
    items(DayOfWeek.values()) { DayHeaderComponent(data.cameraDate.with(it)) }
    item(span = { GridItemSpan(cells) }) { Spacer(modifier = Modifier.height(4.dp)) }
    items(data.offsetStart) {
        CalendarViewMainValueComponent(
            selection = selection,
            data = CalendarDateData(otherMonth = true)
        )
    }
    items(data.days) { dayIndex ->

        val date = when (config.style) {
            CalendarStyle.MONTH -> data.cameraDate.withDayOfMonth(dayIndex.plus(1))
            CalendarStyle.WEEK -> data.weekCameraDate.plusDays(dayIndex.toLong() + data.offsetStart)
        }

        val dateData = calcCalendarDateData(
            date = date,
            calendarViewData = data,
            today = today,
            selection = selection,
            config = config,
            selectedDate = selectedDate,
            selectedDates = selectedDates,
            selectedRange = selectedRange
        ) ?: return@items

        CalendarViewMainValueComponent(
            data = dateData,
            selection = selection,
            onDateClick = onSelect
        )
    }
}
