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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.views.base.FrameBase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FrameBaseTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun frameDisplaysContent() {
        var contentCalled = false
        rule.setContent {
            FrameBase(
                header = null,
                buttonsVisible = false,
                buttons = {},
                layout = { contentCalled = true }
            )
        }
        rule.onNodeWithTag(TestTags.FRAME_BASE_CONTENT).assertExists()
        assert(contentCalled)
    }

    @Test
    fun frameDisplaysNoHeader() {
        rule.setContent {
            FrameBase(
                header = null,
                buttonsVisible = false,
                buttons = {},
                layout = { }
            )
        }
        rule.onNodeWithTag(TestTags.FRAME_BASE_HEADER).assertDoesNotExist()
        rule.onNodeWithTag(TestTags.FRAME_BASE_NO_HEADER).assertExists()
    }

    @Test
    fun frameDisplaysHeader() {
        rule.setContent {
            FrameBase(
                header = Header.Custom {},
                buttonsVisible = false,
                buttons = {},
                layout = { }
            )
        }
        rule.onNodeWithTag(TestTags.FRAME_BASE_NO_HEADER).assertDoesNotExist()
        rule.onNodeWithTag(TestTags.FRAME_BASE_HEADER).assertExists()
    }

    @Test
    fun frameDisplaysButtons() {
        var buttonsCalled = false
        rule.setContent {
            FrameBase(
                header = null,
                buttonsVisible = true,
                buttons = { buttonsCalled = true },
                layout = { }
            )
        }
        rule.onNodeWithTag(TestTags.FRAME_BASE_BUTTONS).assertExists()
        rule.onNodeWithTag(TestTags.FRAME_BASE_NO_BUTTONS).assertDoesNotExist()
        assert(buttonsCalled)
    }

    @Test
    fun frameDisplaysNoButtons() {
        var buttonsCalled = false
        rule.setContent {
            FrameBase(
                header = null,
                buttonsVisible = false,
                buttons = { buttonsCalled = true },
                layout = { }
            )
        }
        rule.onNodeWithTag(TestTags.FRAME_BASE_BUTTONS).assertDoesNotExist()
        rule.onNodeWithTag(TestTags.FRAME_BASE_NO_BUTTONS).assertExists()
        assert(!buttonsCalled)
    }

}