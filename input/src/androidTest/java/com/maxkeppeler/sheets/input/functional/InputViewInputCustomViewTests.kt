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
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.input.InputView
import com.maxkeppeler.sheets.input.models.InputCustomView
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputCustomViewTests {

    @get:Rule
    val rule = createComposeRule()

    private val testHeaderTitle = "This is a title"
    private val testHeaderBody = "This is a body"
    private val testHeaderIcon = IconSource(Icons.Filled.Face)

    @Test
    fun inputViewDisplaysInputCustomView() {
        var testCustomViewCalled = false
        val testInputs = listOf(
            InputCustomView(
                view = { testCustomViewCalled = true }
            )
        )
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        assert(testCustomViewCalled)
    }

    @Test
    fun inputViewDisplaysInputCustomViewHeader() {
        val testInputs = listOf(
            InputCustomView(
                view = {},
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
    fun inputViewDisplaysInputCustomViewWithHeaderTitle() {
        val testInputs = listOf(
            InputCustomView(
                view = {},
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
    fun inputViewDisplaysInputCustomViewWithHeaderBody() {
        val testInputs = listOf(
            InputCustomView(
                view = {},
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
    fun inputViewDisplaysInputCustomViewWithHeaderIcon() {
        val testInputs = listOf(
            InputCustomView(
                view = {},
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
    fun inputViewDisplaysInputCustomViewWithHeaderIconTitleBody() {
        val testInputs = listOf(
            InputCustomView(
                view = {},
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