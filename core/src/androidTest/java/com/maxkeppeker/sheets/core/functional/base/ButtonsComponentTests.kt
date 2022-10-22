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

package com.maxkeppeker.sheets.core.functional.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeler.sheets.test.utils.onExtraButton
import com.maxkeppeler.sheets.test.utils.onNegativeButton
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonsComponentTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun buttonsComponentInvokesValidPositiveButton() {
        var positiveCalled = false
        var negativeCalled = false
        var closeCalled = false
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {},
                onPositive = { positiveCalled = true },
                onNegative = { negativeCalled = true },
                onClose = { closeCalled = true },
                onPositiveValid = true,
            )
        }
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()
        assert(positiveCalled)
        assert(!negativeCalled)
        assert(closeCalled)
    }

    @Test
    fun buttonsComponentInvokesNotInvalidPositiveButton() {
        var positiveCalled = false
        var negativeCalled = false
        var closeCalled = false
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {},
                onPositive = { positiveCalled = true },
                onNegative = { negativeCalled = true },
                onClose = { closeCalled = true },
                onPositiveValid = false,
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
        rule.onPositiveButton().performClick()
        assert(!positiveCalled)
        assert(!negativeCalled)
        assert(!closeCalled)
    }

    @Test
    fun buttonsComponentInvokesNegativeButton() {
        var positiveCalled = false
        var negativeCalled = false
        var closeCalled = false
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {},
                onPositive = { positiveCalled = true },
                onNegative = { negativeCalled = true },
                onClose = { closeCalled = true },
                onPositiveValid = false,
            )
        }
        rule.onNegativeButton().performClick()
        assert(!positiveCalled)
        assert(negativeCalled)
        assert(closeCalled)
    }

    @Test
    fun buttonsComponentInvokesExtraButton() {
        var positiveCalled = false
        var negativeCalled = false
        var closeCalled = false
        var extraCalled = false
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val extraButton = SelectionButton("Test button")
                    override val onExtraButtonClick = { extraCalled = true }
                },
                onPositive = { positiveCalled = true },
                onNegative = { negativeCalled = true },
                onClose = { closeCalled = true },
                onPositiveValid = false,
            )
        }
        rule.onExtraButton().performClick()
        assert(extraCalled)
        assert(!positiveCalled)
        assert(!negativeCalled)
        assert(!closeCalled)
    }

    @Test
    fun buttonsComponentDisplaysPositiveButtonText() {
        val text = "test-text-positive"
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val positiveButton = SelectionButton(text)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = false,
            )
        }
        rule.onPositiveButton().apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun buttonsComponentDisplaysNegativeButtonText() {
        val text = "test-text-negative"
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val negativeButton = SelectionButton(text)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = false,
            )
        }
        rule.onNegativeButton().apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun buttonsComponentDisplaysExtraButtonText() {
        val text = "test-text-extra"
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val extraButton = SelectionButton(text)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = false,
            )
        }
        rule.onExtraButton().apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun buttonsComponentDisplaysPositiveButtonIcon() {
        val icon = IconSource(Icons.Rounded.Face)
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val positiveButton = SelectionButton("", icon)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = false,
            )
        }
        rule.onNodeWithTags(
            TestTags.BUTTON_POSITIVE,
            TestTags.BUTTON_ICON
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }

    @Test
    fun buttonsComponentDisplaysNegativeButtonIcon() {
        val icon = IconSource(Icons.Rounded.Face)
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val negativeButton = SelectionButton("", icon)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = false,
            )
        }
       rule.onNodeWithTags(
            TestTags.BUTTON_NEGATIVE,
            TestTags.BUTTON_ICON
        ).apply {
           assertExists()
           assertIsDisplayed()
       }
    }

    @Test
    fun buttonsComponentDisplaysExtraButtonIcon() {
        val icon = IconSource(Icons.Rounded.Face)
        rule.setContent {
            ButtonsComponent(
                selection = object : BaseSelection() {
                    override val extraButton = SelectionButton("", icon)
                },
                onPositive = { },
                onNegative = { },
                onClose = { },
                onPositiveValid = true,
            )
        }
        rule.onNodeWithTags(
            TestTags.BUTTON_EXTRA,
            TestTags.BUTTON_ICON
        ).apply {
            assertExists()
            assertIsDisplayed()
        }
    }
}