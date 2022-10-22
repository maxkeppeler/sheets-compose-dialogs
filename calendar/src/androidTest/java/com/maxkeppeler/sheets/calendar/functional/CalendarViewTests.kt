/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.calendar.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class CalendarViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun calendarViewDateSelectionSuccess() {
        val testDate = LocalDate.now()
            .withDayOfMonth(12)

        var selectedDate: LocalDate? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Date { selectedDate = it },
            )
        }
        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE_SELECTION,
            testDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedDate != null)
    }

    @Test
    fun calendarViewDateSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Date { },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun calendarViewDatesSelectionSuccess() {
        val testDates = listOf(
            LocalDate.now().withDayOfMonth(2),
            LocalDate.now().withDayOfMonth(8),
            LocalDate.now().withDayOfMonth(14),
            LocalDate.now().withDayOfMonth(27),
        )

        var selectedDates = listOf<LocalDate>()
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Dates { selectedDates = it },
            )
        }

        testDates.forEach { testDate ->
            rule.onNodeWithTags(
                TestTags.CALENDAR_DATE_SELECTION,
                testDate.format(DateTimeFormatter.ISO_DATE)
            ).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(testDates == selectedDates)
    }

    @Test
    fun calendarViewDatesSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Dates { },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun calendarViewPeriodSelectionSuccess() {
        val testStartDate = LocalDate.now().withDayOfMonth(2)
        val testEndDate = LocalDate.now().withDayOfMonth(12)

        var startDate: LocalDate? = null
        var endDate: LocalDate? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Period { start, end ->
                    startDate = start
                    endDate = end
                },
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE_SELECTION,
            testStartDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE_SELECTION,
            testEndDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onPositiveButton().performClick()
        assert(startDate == testStartDate)
        assert(endDate == testEndDate)
    }

    @Test
    fun calendarViewPeriodSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Period { _, _ -> },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun calendarViewPeriodSelectionInvalidSelectEndDateBeforeStartDate() {
        val testStartDate = LocalDate.now().withDayOfMonth(12)
        val testEndDate = LocalDate.now().withDayOfMonth(2)
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Period { _, _ -> },
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE_SELECTION,
            testStartDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE_SELECTION,
            testEndDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun calendarViewDisplaysCalendarStyleWeek() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Date { },
                config = CalendarConfig(style = CalendarStyle.WEEK)
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun calendarViewDisplaysCalendarStyleMonth() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                sheetState = SheetState(visible = true),
                selection = CalendarSelection.Date { },
                config = CalendarConfig(style = CalendarStyle.MONTH)
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

}