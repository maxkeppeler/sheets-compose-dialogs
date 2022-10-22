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

//    @Test
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

//    @Test
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