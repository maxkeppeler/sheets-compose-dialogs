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

import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class InputViewTests {

    @get:Rule
    val rule = createComposeRule()

    private val testOptions = listOf(
        "Option 1", "Option 2"
    )

    @Test
    fun inputViewInputCorrectSequence() {
        var customViewCalled = false
        val testInputs = listOf(
            InputDivider(),
            InputText(text = "text"),
            InputCustomView(view = { customViewCalled = true }),
            InputTextField(),
            InputRadioButtonGroup(items = testOptions),
            InputCheckboxGroup(items = testOptions),
            InputCheckbox(text = testOptions.first())
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_DIVIDER, 0).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT, 1).assertExists()
        assert(customViewCalled)
        rule.onNodeWithTags(TestTags.INPUT_ITEM_TEXT_FIELD, 3).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP, 4).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP, 5).assertExists()
        rule.onNodeWithTags(TestTags.INPUT_ITEM_CHECKBOX, 6).assertExists()
    }

    @Test
    fun inputViewInputCorrectResultIndicesAndValues() {
        var testResultCalled = false
        var testBundle: Bundle? = null
        val testInputText = "inputTestValue"
        val testInputTextField = "inputTestFieldValue"
        val testRadioButtonGroupSelectedIndex = 1
        val testCheckboxGroupSelectedIndex = 0
        val testCheckboxEnabled = true

        val testInputs = listOf(
            InputDivider(),
            InputText(
                text = testInputText
            ),
            InputCustomView(
                view = { }
            ),
            InputTextField(
                text = testInputTextField
            ),
            InputRadioButtonGroup(
                items = testOptions,
                selectedIndex = testRadioButtonGroupSelectedIndex
            ),
            InputCheckboxGroup(
                items = testOptions,
                enabledIndices = listOf(testCheckboxGroupSelectedIndex)
            ),
            InputCheckbox(
                text = testOptions.first(),
                enabled = testCheckboxEnabled
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(
                    input = testInputs,
                    onPositiveClick = {
                        testResultCalled = true
                        testBundle = it
                    }
                ),
            )
        }

        rule.onPositiveButton().performClick()

        assert(testResultCalled)

        assert(testBundle?.containsKey("0") == false) // Divider holds no data

        assert(testBundle?.containsKey("1") == false) // Text holds no data

        assert(testBundle?.containsKey("2") == false) // CustomView holds no data

        assert(testBundle?.containsKey("3") == true) // TextField holds data
        assert(testBundle?.getString("3") == testInputTextField)

        assert(testBundle?.containsKey("4") == true) // RadioButtonGroup holds data
        assert(testBundle?.getInt("4") == testRadioButtonGroupSelectedIndex)

        assert(testBundle?.containsKey("5") == true) // CheckBoxGroup holds data
        assert(testBundle?.getInt("5") == testCheckboxGroupSelectedIndex)

        assert(testBundle?.containsKey("6") == true) // CheckBox holds data
        assert(testBundle?.getBoolean("6") == testCheckboxEnabled)
    }

    @Test
    fun inputViewInputCorrectResultKeysAndValues() {
        var testResultCalled = false
        var testBundle: Bundle? = null
        val testInputText = "inputTestValue"
        val testInputTextField = "inputTestFieldValue"
        val testRadioButtonGroupSelectedIndex = 1
        val testCheckboxGroupSelectedIndex = 0
        val testCheckboxEnabled = true
        val testInputs = listOf(
            InputDivider(),
            InputText(
                text = testInputText
            ),
            InputCustomView(
                view = { }
            ),
            InputTextField(
                key = "key1",
                text = testInputTextField
            ),
            InputRadioButtonGroup(
                key = "key2",
                items = testOptions,
                selectedIndex = testRadioButtonGroupSelectedIndex
            ),
            InputCheckboxGroup(
                key = "key3",
                items = testOptions,
                enabledIndices = listOf(testCheckboxGroupSelectedIndex)
            ),
            InputCheckbox(
                key = "key4",
                text = testOptions.first(),
                enabled = testCheckboxEnabled
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(
                    input = testInputs,
                    onPositiveClick = {
                        testResultCalled = true
                        testBundle = it
                    }
                ),
            )
        }

        rule.onPositiveButton().performClick()

        assert(testResultCalled)

        assert(testBundle?.containsKey("0") == false) // Divider holds no data

        assert(testBundle?.containsKey("1") == false) // Text holds no data

        assert(testBundle?.containsKey("2") == false) // CustomView holds no data

        assert(testBundle?.containsKey("key1") == true) // TextField holds data
        assert(testBundle?.getString("key1") == testInputTextField)

        assert(testBundle?.containsKey("key2") == true) // RadioButtonGroup holds data
        assert(testBundle?.getInt("key2") == testRadioButtonGroupSelectedIndex)

        assert(testBundle?.containsKey("key3") == true) // CheckBoxGroup holds data
        assert(testBundle?.getInt("key3") == testCheckboxGroupSelectedIndex)

        assert(testBundle?.containsKey("key4") == true) // CheckBox holds data
        assert(testBundle?.getBoolean("key4") == testCheckboxEnabled)
    }

}