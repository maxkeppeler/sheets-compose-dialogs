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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.input.functional

import androidx.compose.material.ExperimentalMaterialApi
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
import com.maxkeppeler.sheets.input.models.InputCheckboxGroup
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputCheckboxGroupTestsCustom {

    @get:Rule
    val rule = createComposeRule()

    private val testHeaderTitle = "This is a title"
    private val testHeaderBody = "This is a body"
    private val testHeaderIcon = IconSource(Icons.Filled.Face)

    private val testOptions = listOf(
        "Option 1", "Option 2", "Option 3", "Option 4"
    )

    @Test
    fun inputViewDisplaysInputCheckboxGroupWithTexts() {
        val testInputs = listOf(InputCheckboxGroup(testOptions))
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, text ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index)
                .assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_TEXT, index).apply {
                assertExists()
                assertTextContains(text)
            }
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupDefaultAllUnchecked() {
        val testInputs = listOf(InputCheckboxGroup(testOptions))
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, _ ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index).apply {
                assertExists()
                assertIsOff()
            }
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupAllUnchecked() {
        val testInputs = listOf(InputCheckboxGroup(testOptions))
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, _ ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index).apply {
                assertExists()
                assertIsOff()
            }
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupAllChecked() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                enabledIndices = List(testOptions.size) { index -> index }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, _ ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index).apply {
                assertExists()
                assertIsOn()
            }
        }
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupChangeListener() {
        var changeListenerCalled = false
        var testCheckedIndices: List<Int> = listOf(0, 2)
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                enabledIndices = testCheckedIndices,
                changeListener = {
                    changeListenerCalled = true
                    testCheckedIndices = it
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        listOf(0, 1, 2, 3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).performClick()
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 0).assertExists()
        listOf(0, 2).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index).apply {
                assertExists()
                assertIsOff()
            }
        }
        listOf(1, 3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index).apply {
                assertExists()
                assertIsOn()
            }
        }

        assert(changeListenerCalled)
        assert(testCheckedIndices == listOf(1, 3))
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupResultListener() {
        var resultListenerCalled = false
        var testCheckedIndices: List<Int>? = null
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                resultListener = {
                    resultListenerCalled = true
                    testCheckedIndices = it
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        listOf(0, 1, 3, 1, 3, 2).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(resultListenerCalled)
        assert(testCheckedIndices == listOf(0, 2))
    }

    @Test
    fun inputViewDisplaysInputCheckboxGroupRequiredOverlay() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                required = true
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
    fun inputViewDisplaysInputCheckboxGroupHeader() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                header = InputHeader(),
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
    fun inputViewDisplaysInputCheckboxGroupWithHeaderTitle() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                header = InputHeader(
                    title = testHeaderTitle,
                ),
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
    fun inputViewDisplaysInputCheckboxGroupWithHeaderBody() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                header = InputHeader(
                    body = testHeaderBody,
                ),
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
    fun inputViewDisplaysInputCheckboxGroupWithHeaderIcon() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                header = InputHeader(
                    icon = testHeaderIcon,
                ),
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
    fun inputViewDisplaysInputCheckboxGroupWithHeaderIconTitleBody() {
        val testInputs = listOf(
            InputCheckboxGroup(
                items = testOptions,
                header = InputHeader(
                    title = testHeaderTitle,
                    body = testHeaderBody,
                    icon = testHeaderIcon,
                ),
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