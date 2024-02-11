/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.input.InputView
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputRadioButtonGroup
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputRadioButtonGroupTests {

    @get:Rule
    val rule = createComposeRule()

    private val testHeaderTitle = "This is a title"
    private val testHeaderBody = "This is a body"
    private val testHeaderIcon = IconSource(Icons.Filled.Face)

    private val testOptions = listOf(
        "Option 1", "Option 2", "Option 3", "Option 4"
    )

    @Test
    fun inputViewDisplaysInputRadioButtonGroupTexts() {
        val testInputs = listOf(InputRadioButtonGroup(testOptions))
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, text ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_TEXT, index).apply {
                assertExists()
                assertTextContains(text)
            }
        }
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupDefaultNoSelection() {
        val testInputs = listOf(InputRadioButtonGroup(testOptions))
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 0).assertExists()
        testOptions.forEachIndexed { index, _ ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .apply {
                    assertExists()
                    assertIsNotSelected()
                }
        }
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupOneSelected() {
        val testSelectedIndex = 1
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                selectedIndex = testSelectedIndex
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 0).assertExists()
        listOf(0, 2, 3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .apply {
                    assertExists()
                    assertIsNotSelected()
                }
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, testSelectedIndex).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, testSelectedIndex).apply {
            assertExists()
            assertIsSelected()
        }
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupOnlyOneSelectable() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                selectedIndex = 1,
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }

        listOf(0, 1, 3, 2, 1, 0, 3, 2).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).performClick()
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 0).assertExists()
        listOf(0, 1, 3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .apply {
                    assertExists()
                    assertIsNotSelected()
                }
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, 2).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, 2).apply {
            assertExists()
            assertIsSelected()
        }
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupChangeListener() {
        var changeListenerCalled = false
        var testCheckedIndices = 2
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                selectedIndex = testCheckedIndices,
                changeListener = {
                    changeListenerCalled = true
                    testCheckedIndices = it
                }
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }

        listOf(0, 1, 2, 3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).performClick()
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 0).assertExists()
        listOf(0, 1, 2).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .apply {
                    assertExists()
                    assertIsNotSelected()
                }
        }
        listOf(3).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).assertExists()
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .apply {
                    assertExists()
                    assertIsSelected()
                }
        }

        assert(changeListenerCalled)
        assert(testCheckedIndices == 3)
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupResultListener() {
        var resultListenerCalled = false
        var testCheckedIndices: Int? = 2
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                resultListener = {
                    resultListenerCalled = true
                    testCheckedIndices = it
                }
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }

        listOf(0, 1, 3, 1, 3, 2).forEach { index ->
            rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index).performClick()
        }

        rule.onPositiveButton().performClick()
        assert(resultListenerCalled)
        assert(testCheckedIndices == 2)
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupRequiredOverlay() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                required = true
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_OVERLAY, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupHeader() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                header = InputHeader(),
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupWithHeaderTitle() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                header = InputHeader(
                    title = testHeaderTitle,
                ),
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
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
    fun inputViewDisplaysInputRadioButtonGroupWithHeaderBody() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                header = InputHeader(
                    body = testHeaderBody,
                ),
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
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
    fun inputViewDisplaysInputRadioButtonGroupWithHeaderIcon() {
        val testInputs = listOf(
            InputRadioButtonGroup(
                items = testOptions,
                header = InputHeader(
                    icon = testHeaderIcon,
                ),
            )
        )
        rule.setContent {
            InputView(
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_ICON, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputRadioButtonGroupWithHeaderIconTitleBody() {
        val testInputs = listOf(
            InputRadioButtonGroup(
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
                useCaseState = UseCaseState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_TITLE, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_BODY, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_HEADER_ICON, 0).assertExists()
    }

}