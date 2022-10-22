@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.clock.ClockView
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
class ClockViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun clockView24HourFormatSelectionSuccess() {
        val testTime = LocalTime.of(10, 30)
        var selectedTime: LocalTime? = null
        rule.setContentAndWaitForIdle {
            ClockView(
                sheetState = SheetState(visible = true),
                selection = ClockSelection.HoursMinutes { hours, minutes ->
                    selectedTime = LocalTime.of(hours, minutes)
                },
                config = ClockConfig(is24HourFormat = true)
            )
        }
        listOf(1, 0, 3, 0).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun clockView12HourFormatAmSelectionSuccess() {
        val testTime = LocalTime.of(10, 30)
        var selectedTime: LocalTime? = null
        rule.setContentAndWaitForIdle {
            ClockView(
                sheetState = SheetState(visible = true),
                selection = ClockSelection.HoursMinutes { hours, minutes ->
                    selectedTime = LocalTime.of(hours, minutes)
                },
                config = ClockConfig(is24HourFormat = false)
            )
        }
        listOf(1, 0, 3, 0).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onNodeWithTags(TestTags.CLOCK_12_HOUR_FORMAT, 0).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun clockView12HourFormatPmSelectionSuccess() {
        val testTime = LocalTime.of(20, 30)
        var selectedTime: LocalTime? = null
        rule.setContentAndWaitForIdle {
            ClockView(
                sheetState = SheetState(visible = true),
                selection = ClockSelection.HoursMinutes { hours, minutes ->
                    selectedTime = LocalTime.of(hours, minutes)
                },
                config = ClockConfig(is24HourFormat = false)
            )
        }
        listOf(0, 8, 3, 0).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onNodeWithTags(TestTags.CLOCK_12_HOUR_FORMAT, 1).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun clockViewSelectionAlwaysValid() {
        rule.setContentAndWaitForIdle {
            ClockView(
                sheetState = SheetState(visible = true),
                selection = ClockSelection.HoursMinutes { _, _ -> },
            )
        }
        rule.onPositiveButton().assertIsEnabled()
    }
}