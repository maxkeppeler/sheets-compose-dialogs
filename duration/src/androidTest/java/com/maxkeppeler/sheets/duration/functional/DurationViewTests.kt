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
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.duration.DurationView
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.utils.Constants
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class DurationViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun durationView_hhmm_format_selection() {
        val testTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(30))
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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
        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_mmss_format_selection() {
        val testTime = TimeUnit.MINUTES.toSeconds(1).plus(TimeUnit.SECONDS.toSeconds(90))
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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
        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_hhmmss_format_selection() {
        val testTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(90)).plus(25)
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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
        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_hh_format_selection() {
        val testTime = TimeUnit.HOURS.toSeconds(9)
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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
        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_mm_format_selection() {
        val testTime = TimeUnit.MINUTES.toSeconds(45)
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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
        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_ss_format_selection() {
        val testTime = 42L
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
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

        assertEquals(selectedTime, testTime)
    }

    @Test
    fun durationView_backspace_button() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_BACKSPACE).performClick()
        rule.onPositiveButton().assertIsEnabled()
    }

    @Test
    fun durationView_clear_button() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS, displayClearButton = true)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 4).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_CLEAR).performClick()
        rule.onPositiveButton().assertIsEnabled()
    }

    @Test
    fun durationView_backspace_button_disabled() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 15
                )
            )
        }

        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_BACKSPACE).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_clear_button_disabled() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    displayClearButton = true,
                    minTime = 15
                )
            )
        }

        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 4).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_CLEAR).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }


    @Test
    fun durationView_empty_input_allowed() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }

        repeat(3) { rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick() }
        rule.onPositiveButton().assertIsEnabled()
    }

    @Test
    fun durationView_buttonDisabledWhenValueBelowMinTimeLimit1() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 1
                )
            )
        }
        repeat(3) { rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick() }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_buttonDisabledWhenValueBelowMinTimeLimit() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 10,
                    maxTime = 60
                )
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_buttonDisabledWhenValueAboveMaxTimeLimit() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 10,
                    maxTime = 60
                )
            )
        }
        repeat(70) { rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick() }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_buttonEnabledWhenValueWithinTimeLimits() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 10,
                    maxTime = 60
                )
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 2).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick()
        rule.onPositiveButton().assertIsEnabled()
    }

    @Test
    fun durationView_buttonEnabledWhenValueEqualsMinTimeLimit() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 10,
                    maxTime = 60
                )
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 1).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick()
        rule.onPositiveButton().assertIsEnabled()
    }

    @Test
    fun durationView_buttonDisabledWhenValueEqualsMaxTimeLimit() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    minTime = 10,
                    maxTime = 60
                )
            )
        }
        repeat(60) { rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 0).performClick() }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_keyboard_input0() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }

        rule.onNodeWithText("1").performClick()
        rule.onNodeWithText("0").performClick()
        rule.onNodeWithText("0").performClick()
        rule.onNodeWithText("0").performClick()
        rule.onNodeWithText("0").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 0L)
    }

    @Test
    fun durationView_keyboard_input1to2() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }

        rule.onNodeWithText("1").performClick()
        rule.onNodeWithText("2").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 12L)
    }

    @Test
    fun durationView_keyboard_input3to4() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }

        rule.onNodeWithText("3").performClick()
        rule.onNodeWithText("4").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 34L)
    }

    @Test
    fun durationView_keyboard_input5to6() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        rule.onNodeWithText("5").performClick()
        rule.onNodeWithText("6").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 56L)
    }

    @Test
    fun durationView_keyboard_input7to8() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        rule.onNodeWithText("7").performClick()
        rule.onNodeWithText("8").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 78L)
    }

    @Test
    fun durationView_keyboard_input9() {
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(timeFormat = DurationFormat.SS)
            )
        }
        rule.onNodeWithText("9").performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(selectedTime, 9L)
    }

    @Test
    fun durationView_currentTime() {
        val currentTime = 45L
        var selectedTime = 0L
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {
                    selectedTime = it
                },
                config = DurationConfig(timeFormat = DurationFormat.SS, currentTime = currentTime)
            )
        }
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_maxTime() {
        val maxTime = 60L
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS, maxTime = maxTime)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 6).performClick()
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, 1).performClick()
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun durationView_displayClearButton() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS, displayClearButton = true)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_CLEAR).assertExists()
    }

    @Test
    fun durationView_hideClearButton() {
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection {},
                config = DurationConfig(timeFormat = DurationFormat.SS, displayClearButton = false)
            )
        }
        rule.onNodeWithTags(TestTags.KEYBOARD_KEY, Constants.ACTION_CLEAR).assertDoesNotExist()
    }

    @Test
    fun durationView_currentTime_HH_MM_SS() {
        val currentTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(30)).plus(25)
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.HH_MM_SS,
                    currentTime = currentTime
                )
            )
        }

        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_currentTime_HH_MM() {
        val currentTime = TimeUnit.HOURS.toSeconds(1).plus(TimeUnit.MINUTES.toSeconds(30))
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.HH_MM,
                    currentTime = currentTime
                )
            )
        }
        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_currentTime_MM_SS() {
        val currentTime = TimeUnit.MINUTES.toSeconds(1).plus(TimeUnit.SECONDS.toSeconds(30))
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.MM_SS,
                    currentTime = currentTime
                )
            )
        }
        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_currentTime_M_SS() {
        val currentTime = TimeUnit.MINUTES.toSeconds(5).plus(TimeUnit.SECONDS.toSeconds(30))
        var selectedTime: Long? = null
        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.M_SS,
                    currentTime = currentTime
                )
            )
        }
        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_currentTime_HH() {
        val currentTime = TimeUnit.HOURS.toSeconds(9)
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.HH,
                    currentTime = currentTime
                )
            )
        }
        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_currentTime_SS() {
        val currentTime = 42L
        var selectedTime: Long? = null

        rule.setContentAndWaitForIdle {
            DurationView(
                useCaseState = UseCaseState(visible = true),
                selection = DurationSelection { time ->
                    selectedTime = time
                },
                config = DurationConfig(
                    timeFormat = DurationFormat.SS,
                    currentTime = currentTime
                )
            )
        }

        rule.onPositiveButton().performClick()
        assertEquals(currentTime, selectedTime)
    }

    @Test
    fun durationView_checkSetup_negativeCurrentTime() {
        val config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            currentTime = -1
        )

        val result = runCatching {
            rule.setContentAndWaitForIdle {
                DurationView(
                    useCaseState = UseCaseState(visible = true),
                    selection = DurationSelection {},
                    config = config
                )
            }
        }
        assertTrue(result.isFailure)
    }

    @Test
    fun durationView_checkSetup_negativeMinTime() {
        val config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            minTime = -1
        )

        val result = runCatching {
            rule.setContentAndWaitForIdle {
                DurationView(
                    useCaseState = UseCaseState(visible = true),
                    selection = DurationSelection {},
                    config = config
                )
            }
        }
        assertTrue(result.isFailure)
    }

    @Test
    fun durationView_checkSetup_negativeMaxTime() {
        val config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            maxTime = -1
        )

        val result = runCatching {
            rule.setContentAndWaitForIdle {
                DurationView(
                    useCaseState = UseCaseState(visible = true),
                    selection = DurationSelection {},
                    config = config
                )
            }
        }
        assertTrue(result.isFailure)
    }

    @Test
    fun durationView_checkSetup_minGreaterThanMaxTime() {
        val config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            minTime = 10,
            maxTime = 5
        )
        val result = runCatching {
            rule.setContentAndWaitForIdle {
                DurationView(
                    useCaseState = UseCaseState(visible = true),
                    selection = DurationSelection {},
                    config = config
                )
            }
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun durationView_checkSetup_validConfig() {
        val config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            currentTime = 0,
            minTime = 0,
            maxTime = 60
        )
        val result = runCatching {
            rule.setContentAndWaitForIdle {
                DurationView(
                    useCaseState = UseCaseState(visible = true),
                    selection = DurationSelection {},
                    config = config
                )
            }
        }
        assertTrue(result.isSuccess)
    }


}