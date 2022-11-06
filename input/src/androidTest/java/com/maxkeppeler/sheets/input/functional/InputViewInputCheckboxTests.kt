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

package com.maxkeppeler.sheets.input.functional

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.input.InputView
import com.maxkeppeler.sheets.input.models.InputCheckbox
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputCheckboxTests {

    @get:Rule
    val rule = createComposeRule()

    private val testHeaderTitle = "This is a title"
    private val testHeaderBody = "This is a body"
    private val testHeaderIcon = IconSource(Icons.Filled.Face)

    @Test
    fun inputViewDisplaysInputCheckboxWithText() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(testText)
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).apply {
            assertExists()
            assertIsOff()
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_TEXT, 0).apply {
            assertExists()
            assertTextContains(testText)
        }
    }

    @Test
    fun inputViewDisplaysInputDefaultUnchecked() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(testText)
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).apply {
            assertExists()
            assertIsOff()
        }
    }

    @Test
    fun inputViewDisplaysInputUnchecked() {
        val testText = "This is a checkbox"
        val testChecked = false
        val testInputs = listOf(
            InputCheckbox(testText, testChecked)
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).apply {
            assertExists()
            assertIsOff()
        }
    }

    @Test
    fun inputViewDisplaysInputChecked() {
        val testText = "This is a checkbox"
        val testChecked = true
        val testInputs = listOf(
            InputCheckbox(testText, testChecked)
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).apply {
            assertExists()
            assertIsOn()
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxChangeListener() {
        var changeListenerCalled = false
        var testChecked = false
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                text = testText,
                enabled = testChecked,
                changeListener = {
                    changeListenerCalled = true
                    testChecked = it
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        // Test switching between container & component clicks, both need to work
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).performClick()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).performClick()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).performClick()
        rule.waitForIdle()
        assert(changeListenerCalled)
        assert(testChecked)
    }

    @Test
    fun inputViewDisplaysInputCheckboxResultListener() {
        var resultListenerCalled = false
        var testChecked = false
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                text = testText,
                enabled = testChecked,
                resultListener = {
                    testChecked = it
                    resultListenerCalled = true
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        // Test switching between container & component clicks, both need to work
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 0).performClick()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, 0).performClick()
        rule.onPositiveButton().performClick()
        assert(resultListenerCalled)
        assert(!testChecked)
    }

    @Test
    fun inputViewDisplaysInputCheckboxRequiredOverlay() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_OVERLAY, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputCheckboxHeader() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                header = InputHeader(),
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputCheckboxWithHeaderTitle() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                header = InputHeader(
                    title = testHeaderTitle,
                ),
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_TITLE, 0).apply {
            assertExists()
            assertTextContains(testHeaderTitle)
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxWithHeaderBody() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                header = InputHeader(
                    body = testHeaderBody,
                ),
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_BODY, 0).apply {
            assertExists()
            assertTextContains(testHeaderBody)
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxWithHeaderIcon() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                header = InputHeader(
                    icon = testHeaderIcon,
                ),
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_ICON, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputCheckboxWithHeaderIconTitleBody() {
        val testText = "This is a checkbox"
        val testInputs = listOf(
            InputCheckbox(
                header = InputHeader(
                    title = testHeaderTitle,
                    body = testHeaderBody,
                    icon = testHeaderIcon,
                ),
                text = testText,
                required = true,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_TITLE, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_BODY, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_ICON, 0).assertExists()
    }

}