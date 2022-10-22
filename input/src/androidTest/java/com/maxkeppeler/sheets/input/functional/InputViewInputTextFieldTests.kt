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
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.input.InputView
import com.maxkeppeler.sheets.input.models.*
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputTextFieldTests {

    @get:Rule
    val rule = createComposeRule()

    private val testHeaderTitle = "This is a title"
    private val testHeaderBody = "This is a body"
    private val testHeaderIcon = IconSource(Icons.Filled.Face)

    @Test
    fun inputViewDisplaysInputTextField() {
        val testInputs = listOf(
            InputTextField()
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 0).assertExists()
    }

    @Test
    fun inputViewDisplaysInputTextFieldType() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testInputs = listOf(InputTextField())
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).assertExists()
    }

    @Test
    fun inputViewDisplaysInputTextFieldTypeDefault() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testInputs = listOf(InputTextField(type = testTextFieldType))
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).assertExists()
    }

    @Test
    fun inputViewDisplaysInputTextFieldTypeOutlined() {
        val testTextFieldType = InputTextFieldType.OUTLINED
        val testInputs = listOf(InputTextField(type = testTextFieldType))
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).assertExists()
    }

    @Test
    fun inputViewDisplaysInputTextFieldText() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testText = "Some text ... "
        val testInputs = listOf(
            InputTextField(
                text = testText,
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType)
            .apply {
                assertExists()
                assertTextContains(testText)
            }
    }


    @Test
    fun inputViewDisplaysInputTextFieldValidationInvalid() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testText = "Some text ... "
        var validationCalled = false
        val validationErrorMessage = "Not valid bc ..."
        val testInputs = listOf(
            InputTextField(
                validationListener = {
                    validationCalled = true
                    ValidationResult.Invalid(validationErrorMessage)
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType)
            .performTextInput(testText)

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).apply {
            assertExists()
            assertTextContains(testText)
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_ERROR_TEXT).apply {
            assertExists()
            assertTextContains(validationErrorMessage)
        }

        assert(validationCalled)
    }

    @Test
    fun inputViewDisplaysInputTextFieldValidationValid() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testText = "Some text ... "
        var validationCalled = false
        val testInputs = listOf(
            InputTextField(
                validationListener = {
                    validationCalled = true
                    ValidationResult.Valid
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType)
            .performTextInput(testText)

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).apply {
            assertExists()
            assertTextContains(testText)
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_ERROR_TEXT).assertDoesNotExist()
        assert(validationCalled)
    }


    @Test
    fun inputViewDisplaysInputTextFieldChangeListener() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testText = "Some text ... "
        var changeListenerCalled = false
        var testChangedText: String? = null
        val testInputs = listOf(
            InputTextField(
                changeListener = {
                    changeListenerCalled = true
                    testChangedText = it
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType)
            .performTextInput(testText)

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).apply {
            assertExists()
            assertTextContains(testText)
        }

        assert(changeListenerCalled)
        assert(testChangedText == testText)
    }

    @Test
    fun inputViewDisplaysInputTextFieldResultListener() {
        val testTextFieldType = InputTextFieldType.DEFAULT
        val testText = "Some text ... "
        var resultListenerCalled = false
        var testResultText: String? = null
        val testInputs = listOf(
            InputTextField(
                resultListener = {
                    resultListenerCalled = true
                    testResultText = it
                }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType)
            .performTextInput(testText)
        rule.onPositiveButton().performClick()

        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD_TYPE, testTextFieldType).apply {
            assertExists()
            assertTextContains(testText)
        }

        assert(resultListenerCalled)
        assert(testResultText == testText)
    }

    @Test
    fun inputViewDisplaysInputTextFieldRequiredOverlay() {
        val testInputs = listOf(
            InputTextField(
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
    fun inputViewDisplaysInputTextFieldHeader() {
        val testInputs = listOf(
            InputTextField(
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
    fun inputViewDisplaysInputTextFieldWithHeaderTitle() {
        val testInputs = listOf(
            InputTextField(
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
    fun inputViewDisplaysInputTextFieldWithHeaderBody() {
        val testInputs = listOf(
            InputTextField(
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
    fun inputViewDisplaysInputTextFieldWithHeaderIcon() {
        val testInputs = listOf(
            InputTextField(
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
    fun inputViewDisplaysInputTextFieldWithHeaderIconTitleBody() {
        val testInputs = listOf(
            InputTextField(
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