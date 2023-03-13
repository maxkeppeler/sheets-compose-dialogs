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

package com.maxkeppeler.sheets.info.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.info.InfoView
import com.maxkeppeler.sheets.info.models.InfoBody
import com.maxkeppeler.sheets.info.models.InfoSelection
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun infoViewDisplaysDefaultBody() {
        val text = "This is a default body."
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                useCaseState = UseCaseState(visible = true),
                body = InfoBody.Default(text),
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT).assertExists()
    }

    @Test
    fun infoViewDisplaysDefaultBodyText() {
        val text = "This is a default body."
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                useCaseState = UseCaseState(visible = true),
                body = InfoBody.Default(text),
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT_TEXT).apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun infoViewDisplaysCustomBody() {
        var customBodyCalled = false
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                useCaseState = UseCaseState(visible = true),
                body = InfoBody.Custom { customBodyCalled = true },
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT).assertDoesNotExist()
        assert(customBodyCalled)
    }

}