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

package com.maxkeppeler.sheets.duration.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.duration.DurationView
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.utils.Constants
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class DurationViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun durationViewHHMMSelectionSuccess() {
        val testTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(30))
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.HH_MM)
            )
        }
        listOf(1, 3, 0).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewMMSSSelectionSuccess() {
        val testTime = TimeUnit.MINUTES.toSeconds(1).plus(TimeUnit.SECONDS.toSeconds(90))
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.MM_SS)
            )
        }
        listOf(1, 9, 0).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewHHMMSSSelectionSuccess() {
        val testTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(90)).plus(25)
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.HH_MM_SS)
            )
        }
        listOf(1, 9, 0, 2, 5).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewHHSelectionSuccess() {
        val testTime = TimeUnit.HOURS.toSeconds(9)
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.HH)
            )
        }
        listOf(9).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewMMSelectionSuccess() {
        val testTime = TimeUnit.MINUTES.toSeconds(45)
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.MM)
            )
        }
        listOf(4, 5).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it.toString()).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewSSSelectionSuccess() {
        val testTime = 42L
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        listOf(4, 2).forEach {
            rule.onNodeWithTags(TestTags.KEYBOARD_KEY, it).performClick()
        }
        rule.onPositiveButton().performClick()
        assert(selectedTime == testTime)
    }

    @Test
    fun durationViewBackspace() {
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }

        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_BACKSPACE).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationViewClear() {
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS, displayClearButton = true)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 4).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_CLEAR).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationViewLeadingZerosAreIgnored() {
        rule.setContentAndWaitForIdle {
            DurationView(
                sheetState = SheetState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        repeat(3) { rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick() }
        rule.onPositiveButton().assertIsNotEnabled()
    }

}