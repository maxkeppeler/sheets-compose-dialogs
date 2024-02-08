/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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

import android.util.Range
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class CalendarViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun givenCalendarView_whenDateSelected_thenDateSelectionSuccess() {
        val testDate = LocalDate.now()
            .withDayOfMonth(12)

        var selectedDate: LocalDate? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date { selectedDate = it },
            )
        }
        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedDate != null)
    }

    @Test
    fun givenCalendarView_whenNoDateSelected_thenDateSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date { },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun givenCalendarView_whenMultipleDatesSelected_thenDatesSelectionSuccess() {
        val testDates = listOf(
            LocalDate.now().withDayOfMonth(2),
            LocalDate.now().withDayOfMonth(8),
            LocalDate.now().withDayOfMonth(14),
            LocalDate.now().withDayOfMonth(27),
        )

        var selectedDates = listOf<LocalDate>()
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Dates { selectedDates = it },
            )
        }

        testDates.forEach { testDate ->
            rule.onNodeWithTags(
                TestTags.CALENDAR_DATE,
                testDate.format(DateTimeFormatter.ISO_DATE)
            ).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(testDates == selectedDates)
    }

    @Test
    fun givenCalendarView_whenNoDatesSelected_thenDatesSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Dates { },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun givenCalendarView_whenDateSelectedWithStyleMonthAndDisabledDates_thenDateSelectionStyleMonthConfigDatesDisabled() {
        val testStartDate = LocalDate.now().withDayOfMonth(2)
        val testEndDate = LocalDate.now().withDayOfMonth(12)

        var startDate: LocalDate? = null
        var endDate: LocalDate? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Period { start, end ->
                    startDate = start
                    endDate = end
                },
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testStartDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testEndDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onPositiveButton().performClick()
        assert(startDate == testStartDate)
        assert(endDate == testEndDate)
    }

    @Test
    fun givenCalendarView_whenMultipleDatesSelectedWithStyleMonthAndDisabledDates_thenDatesSelectionStyleMonthConfigDatesDisabled() {
        val testDate = LocalDate.now().withDayOfMonth(15)
        val newDates = listOf(
            testDate.plusDays(2),
            testDate,
            testDate.minusDays(3)
        )
        val disabledDates = listOf(
            testDate.minusDays(3),
        )
        var selectedDate: LocalDate? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(
                    onSelectDate = { dates -> selectedDate = dates }
                ),
                config = CalendarConfig(
                    style = CalendarStyle.MONTH,
                    disabledDates = disabledDates
                )
            )
        }

        newDates.forEach { date ->
            rule.onNodeWithTags(
                TestTags.CALENDAR_DATE,
                date.format(DateTimeFormatter.ISO_DATE)
            ).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(selectedDate == testDate)
    }

    @Test
    fun givenCalendarView_whenPeriodSelectedWithStyleMonthAndDisabledDates_thenPeriodSelectionStyleMonthConfigDatesDisabled() {
        val testDate = LocalDate.now().withDayOfMonth(15)
        val defaultDates = listOf(
            testDate.minusDays(10),
            testDate.minusDays(5)
        )
        val newDates = listOf(
            testDate,
            testDate.minusDays(3)
        )
        val disabledDates = listOf(
            testDate.minusDays(3),
        )
        var selectedDates: List<LocalDate>? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Dates(
                    selectedDates = defaultDates,
                    onSelectDates = { dates -> selectedDates = dates }
                ),
                config = CalendarConfig(
                    style = CalendarStyle.MONTH,
                    disabledDates = disabledDates
                )
            )
        }

        newDates.forEach { date ->
            rule.onNodeWithTags(
                TestTags.CALENDAR_DATE,
                date.format(DateTimeFormatter.ISO_DATE)
            ).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(selectedDates?.sorted() == (defaultDates + testDate).sorted())
    }


    @Test
    fun givenCalendarView_whenPeriodSelectedWithStyleMonthAndDisabledDatesAlt_thenPeriodSelectionStyleMonthConfigDatesDisabled() {
        val testDate = LocalDate.now().withDayOfMonth(15)
        val disabledDates = listOf(
            testDate.minusDays(1),
            testDate.minusDays(2),
            testDate.minusDays(3),
        )
        val testStartDate = testDate.minusDays(4)
        val testEndDate = testDate.plusDays(2)
        var selectedDate: Range<LocalDate>? = null
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Period { startDate, endDate ->
                    selectedDate = Range(startDate, endDate)
                },
                config = CalendarConfig(
                    style = CalendarStyle.MONTH,
                    disabledDates = disabledDates
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testStartDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testEndDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onPositiveButton().assertIsNotEnabled()
        assert(selectedDate == null)
    }

    @Test
    fun givenCalendarView_whenNoPeriodSelected_thenPeriodSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Period { _, _ -> },
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun givenCalendarView_whenEndDateSelectedBeforeStartDate_thenPeriodSelectionInvalidSelectEndDateBeforeStartDate() {
        val testStartDate = LocalDate.now().withDayOfMonth(12)
        val testEndDate = LocalDate.now().withDayOfMonth(2)
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Period { _, _ -> },
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testStartDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testEndDate.format(DateTimeFormatter.ISO_DATE)
        ).performClick()

        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun givenCalendarView_whenCalendarStyleWeek_thenCalendarViewDisplaysCalendarStyleWeek() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date { },
                config = CalendarConfig(style = CalendarStyle.WEEK)
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun givenCalendarView_whenCalendarStyleMonth_thenCalendarViewDisplaysCalendarStyleMonth() {
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date { },
                config = CalendarConfig(style = CalendarStyle.MONTH)
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }


    @Test
    fun givenCalendarView_whenDateSelectedWithCameraDate_thenDisplayCorrectTime() {
        val testDate = LocalDate.now().withDayOfMonth(15)
        val testCameraDate = LocalDate.now().minusMonths(2)
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(
                    selectedDate = testDate,
                    onSelectDate = { date -> }
                ),
                config = CalendarConfig(
                    style = CalendarStyle.MONTH,
                    cameraDate = testCameraDate
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testCameraDate.format(DateTimeFormatter.ISO_DATE)
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarView_whenDateSelectedWithCameraDateOutsideBoundary_thenDisplaySelectedTime() {
        val testDate = LocalDate.now().withDayOfMonth(15)
        val testBoundary = testDate.minusYears(2)..testDate.plusYears(2)
        val testCameraDate = LocalDate.now().minusYears(4)
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(
                    selectedDate = testDate,
                    onSelectDate = { date -> }
                ),
                config = CalendarConfig(
                    boundary = testBoundary,
                    cameraDate = testCameraDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testCameraDate.format(DateTimeFormatter.ISO_DATE)
        ).assertDoesNotExist()

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testDate.format(DateTimeFormatter.ISO_DATE)
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }


    @Test
    fun givenCalendarView_whenCameraDateOutsideBoundaryCurrentTimeInsideBoundary_thenDisplayCurrentTime() {
        val testDate = LocalDate.now()
        val testBoundary = testDate.minusYears(2)..testDate.plusYears(2)
        val testCameraDate = LocalDate.now().minusYears(4)
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(
                    onSelectDate = { date -> }
                ),
                config = CalendarConfig(
                    boundary = testBoundary,
                    cameraDate = testCameraDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testDate.format(DateTimeFormatter.ISO_DATE)
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarView_whenCameraDateOutsideBoundaryCurrentTimeOutsideBoundary_thenDisplayCurrentTime() {
        val testDate = LocalDate.now()
        val testBoundary = testDate.plusYears(2)..testDate.plusYears(4)
        val testCameraDate = LocalDate.now().minusYears(4)
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(
                    onSelectDate = { date -> }
                ),
                config = CalendarConfig(
                    boundary = testBoundary,
                    cameraDate = testCameraDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_DATE,
            testBoundary.start.format(DateTimeFormatter.ISO_DATE)
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarViewWithWeekStyle_whenDisplayCalendarWeeksTrue_thenDisplayCalendarWeek() {
        val testDate = LocalDate.now()
        val calendarWeek = testDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR).toString()
        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(onSelectDate = { }),
                config = CalendarConfig(
                    displayCalendarWeeks = true,
                    style = CalendarStyle.WEEK
                )
            )
        }
        rule.onNodeWithTags(
            TestTags.CALENDAR_CW,
            calendarWeek
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarViewWithMonthStyleOnLastDayOfMonth_whenDisplayCalendarWeeksTrue_thenCorrectCalendarWeekDisplayed() {
        val testDate = LocalDate.of(2023, 12, 31)
        val calendarWeek =
            DateTimeFormatter.ofPattern("w").withLocale(Locale.GERMANY).format(testDate)

        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(onSelectDate = { }),
                config = CalendarConfig(
                    displayCalendarWeeks = true,
                    cameraDate = testDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_CW,
            calendarWeek
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarViewWithMonthStyleOnFirstDayOfMonth_whenDisplayCalendarWeeksTrue_thenCorrectCalendarWeekDisplayed() {
        // Example: Set to the first day of January
        val testDate = LocalDate.of(2024, 1, 1)
        val calendarWeek = testDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR).toString()

        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(onSelectDate = { }),
                config = CalendarConfig(
                    displayCalendarWeeks = true,
                    cameraDate = testDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_CW,
            calendarWeek
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun givenCalendarViewWithMonthStyleDuringYearTransition_whenDisplayCalendarWeeksTrue_thenCorrectCalendarWeekDisplayed() {
        // Example: Set to the last day of the year
        val testDate = LocalDate.of(2023, 12, 31)
        val nextYearDate = testDate.plusDays(1)
        val calendarWeek = nextYearDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR).toString()

        rule.setContentAndWaitForIdle {
            CalendarView(
                useCaseState = UseCaseState(visible = true),
                selection = CalendarSelection.Date(onSelectDate = { }),
                config = CalendarConfig(
                    displayCalendarWeeks = true,
                    cameraDate = nextYearDate,
                    style = CalendarStyle.MONTH
                )
            )
        }

        rule.onNodeWithTags(
            TestTags.CALENDAR_CW,
            calendarWeek
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test(timeout = 1000000)
    fun givenCalendarViewWithMonthStyleAndLocaleGermany_whenIteratingThroughMonths_thenCorrectDataIsDisplayedForEachMonth() =
        runTest(timeout = kotlin.time.Duration.parse("1m")) {
            val startDate = LocalDate.of(2023, 1, 1)
            val endDate = LocalDate.of(2028, 1, 1)
            val testLocale = Locale.GERMANY
            val iterations = Period.between(startDate, endDate).toTotalMonths()
            rule.setContentAndWaitForIdle {
                CalendarView(
                    useCaseState = UseCaseState(visible = true),
                    selection = CalendarSelection.Date(onSelectDate = { }),
                    config = CalendarConfig(
                        displayCalendarWeeks = true,
                        cameraDate = startDate,
                        locale = testLocale,
                        style = CalendarStyle.MONTH
                    )
                )
            }
            repeat(iterations.toInt()) { index ->
                val currentMonth = startDate.plusMonths(index.toLong())

                // Check if the month is displayed
                rule.onNodeWithTags(TestTags.CALENDAR_MONTH_TITLE, currentMonth.month.value).apply {
                    assertExists()
                    assertIsDisplayed()
                }

                // Check if the year is displayed
                rule.onNodeWithTags(TestTags.CALENDAR_YEAR_TITLE, currentMonth.year).apply {
                    assertExists()
                    assertIsDisplayed()
                }

                // Check if the calendar weeks are displayed
                val calendarWeeks = getCalendarWeeksForMonth(testLocale, currentMonth)
                calendarWeeks.forEach { cw ->
                    rule.onNodeWithTags(TestTags.CALENDAR_CW, cw).apply {
                        assertExists()
                        assertIsDisplayed()
                    }
                }

                // Check if the calendar days are displayed
                val calendarDays = getCalendarDayForMonth(currentMonth)
                calendarDays.forEach { day ->
                    val date = currentMonth.withDayOfMonth(day).format(DateTimeFormatter.ISO_DATE)
                    rule.onNodeWithTags(TestTags.CALENDAR_DATE, date).apply {
                        assertExists()
                        assertIsDisplayed()
                    }
                }

                // Navigate to the next month if not the last iteration
                if (index < iterations - 1) {
                    rule.onNodeWithTags(TestTags.CALENDAR_NEXT_ACTION).performClick()
                    rule.awaitIdle()
                }
            }
        }

    @Test(timeout = 1000000)
    fun givenCalendarViewWithMonthStyleAndLocaleUS_whenIteratingThroughMonths_thenCorrectDataIsDisplayedForEachMonth() =
        runTest(timeout = kotlin.time.Duration.parse("1m")) {
            val startDate = LocalDate.of(2023, 1, 1)
            val endDate = LocalDate.of(2028, 1, 1)
            val testLocale = Locale.US
            val iterations = Period.between(startDate, endDate).toTotalMonths()
            rule.setContentAndWaitForIdle {
                CalendarView(
                    useCaseState = UseCaseState(visible = true),
                    selection = CalendarSelection.Date(onSelectDate = { }),
                    config = CalendarConfig(
                        displayCalendarWeeks = true,
                        cameraDate = startDate,
                        locale = testLocale,
                        style = CalendarStyle.MONTH
                    )
                )
            }
            repeat(iterations.toInt()) { index ->
                val currentMonth = startDate.plusMonths(index.toLong())

                // Check if the month is displayed
                rule.onNodeWithTags(TestTags.CALENDAR_MONTH_TITLE, currentMonth.month.value).apply {
                    assertExists()
                    assertIsDisplayed()
                }

                // Check if the year is displayed
                rule.onNodeWithTags(TestTags.CALENDAR_YEAR_TITLE, currentMonth.year).apply {
                    assertExists()
                    assertIsDisplayed()
                }

                // Check if the calendar weeks are displayed
                val calendarWeeks = getCalendarWeeksForMonth(testLocale, currentMonth)
                calendarWeeks.forEach { cw ->
                    rule.onNodeWithTags(TestTags.CALENDAR_CW, cw).apply {
                        assertExists()
                        assertIsDisplayed()
                    }
                }

                // Check if the calendar days are displayed
                val calendarDays = getCalendarDayForMonth(currentMonth)
                calendarDays.forEach { day ->
                    val date = currentMonth.withDayOfMonth(day).format(DateTimeFormatter.ISO_DATE)
                    rule.onNodeWithTags(TestTags.CALENDAR_DATE, date).apply {
                        assertExists()
                        assertIsDisplayed()
                    }
                }

                // Navigate to the next month if not the last iteration
                if (index < iterations - 1) {
                    rule.onNodeWithTags(TestTags.CALENDAR_NEXT_ACTION).performClick()
                    rule.awaitIdle()
                }
            }
        }

    private fun getCalendarWeeksForMonth(locale: Locale, date: LocalDate): Set<Int> {
        val firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth())
        val lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth())

        val weekField = WeekFields.of(locale).weekOfWeekBasedYear()

        val weeksInMonth = mutableSetOf<Int>()
        var currentDate = firstDayOfMonth
        while (!currentDate.isAfter(lastDayOfMonth)) {
            val weekOfYear = currentDate.get(weekField)
            weeksInMonth.add(weekOfYear)
            currentDate = currentDate.plusDays(1)
        }

        return weeksInMonth
    }

    private fun getCalendarDayForMonth(date: LocalDate): Set<Int> {
        val firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth())
        val lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth())
        val weeksInMonth = mutableSetOf<Int>()
        var currentDate = firstDayOfMonth
        while (!currentDate.isAfter(lastDayOfMonth)) {
            val dayOfMonth = currentDate.dayOfMonth
            weeksInMonth.add(dayOfMonth)
            currentDate = currentDate.plusDays(1)
        }

        return weeksInMonth
    }

}