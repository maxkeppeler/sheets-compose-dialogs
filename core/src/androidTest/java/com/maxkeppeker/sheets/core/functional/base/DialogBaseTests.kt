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

package com.maxkeppeker.sheets.core.functional.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.views.base.DialogBase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogBaseTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun dialogVisibleDisplaysContent() {
        var contentCalled = false
        val state = UseCaseState(visible = true)
        rule.setContent {
            DialogBase(
                state = state,
                onDialogClick = {},
                content = { contentCalled = true }
            )
        }
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTAINER).assertExists()
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTENT).assertExists()
        assert(contentCalled)
    }

    @Test
    fun dialogNotVisibleDisplaysNoContent() {
        var contentCalled = false
        val state = UseCaseState(visible = false)
        rule.setContent {
            DialogBase(
                state = state,
                onDialogClick = {},
                content = { contentCalled = true }
            )
        }
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTAINER).assertDoesNotExist()
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTENT).assertDoesNotExist()
        assert(!contentCalled)
    }

    @Test
    fun dialogVisibleAllowsInteraction() {
        var contentClicked = false
        val state = UseCaseState(visible = true)
        rule.setContent {
            DialogBase(
                state = state,
                content = { Column(Modifier.height(200.dp)) {} }, // Otherwise no content / area to click
                onDialogClick = { contentClicked = true }
            )
        }
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTENT).apply {
            assertExists()
            assertHasClickAction()
            performTouchInput { click(center) }
            assert(contentClicked)
        }
    }

    @Test
    fun dialogNotVisibleAllowsNoInteraction() {
        var contentClicked = false
        val state = UseCaseState(visible = false)
        rule.setContent {
            DialogBase(
                state = state,
                content = { },
                onDialogClick = { contentClicked = true }
            )
        }
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTENT).apply {
            assertDoesNotExist()
            assert(!contentClicked)
        }
    }

    @Test
    fun dialogDismissesOnClickOutside() {
        var dismissCall = false
        rule.setContent {
            DialogBase(
                state = UseCaseState(
                    visible = true,
                    onDismissRequest = { dismissCall = true }
                ),
                content = { },
            )
        }
        rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTAINER).apply {
            performTouchInput { click(topLeft) }
            assert(dismissCall)
        }
    }
}