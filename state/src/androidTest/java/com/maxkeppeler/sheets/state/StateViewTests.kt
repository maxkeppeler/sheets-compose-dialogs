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

package com.maxkeppeler.sheets.state

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StateViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun stateViewDisplaysLoadingWithLabel() {
        val labelText = "Please wait a moment..."
        val indicator = ProgressIndicator.Linear()
        val state = State.Loading(labelText, indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).apply {
            assertExists()
            assertIsDisplayed()
            assertTextContains(labelText)
        }
    }

    @Test
    fun stateViewDisplaysLoadingWithNoLabel() {
        val indicator = ProgressIndicator.Linear()
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).assertDoesNotExist()
    }

    @Test
    fun stateViewDisplaysLoadingWithIndeterminateLinearIndicator() {
        val indicator = ProgressIndicator.Linear()
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_LOADING_LINEAR).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithCustomIndeterminateLinearIndicator() {
        var customViewCalled = false
        val indicator = ProgressIndicator.Linear { customViewCalled = true }
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_LOADING_LINEAR).assertDoesNotExist()
        assert(customViewCalled)
    }

    @Test
    fun stateViewDisplaysLoadingWithDeterminateLinearIndicator() {
        val progress = 24f
        val indicator = ProgressIndicator.Linear(progress)
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_LINEAR, progress).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithDeterminateLinearIndicatorAndPercentage() {
        val progress = 24f
        val indicator = ProgressIndicator.Linear(
            value = progress,
            showProgressPercentage = true
        )
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_LINEAR, progress).assertExists()
        rule.onNodeWithTag(TestTags.STATE_LOADING_LABEL_PERCENTAGE).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithCustomDeterminateLinearIndicator() {
        var customViewCalled = false
        var customViewProgress: Float? = null
        val progress = 24f
        val indicator = ProgressIndicator.Linear(
            value = progress,
            customProgressIndicator = { currentProgress ->
                customViewCalled = true
                customViewProgress = currentProgress
            }
        )
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_LINEAR, progress).assertDoesNotExist()
        assert(customViewCalled)
        assert(customViewProgress == progress)
    }


    @Test
    fun stateViewDisplaysLoadingWithIndeterminateCircularIndicator() {
        val indicator = ProgressIndicator.Circular()
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_LOADING_CIRCULAR).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithCustomIndeterminateCircularIndicator() {
        var customViewCalled = false
        val indicator = ProgressIndicator.Circular { customViewCalled = true }
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_LOADING_CIRCULAR).assertDoesNotExist()
        assert(customViewCalled)
    }

    @Test
    fun stateViewDisplaysLoadingWithDeterminateCircularIndicator() {
        val progress = 24f
        val indicator = ProgressIndicator.Circular(progress)
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_CIRCULAR, progress).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithDeterminateCircularIndicatorAndPercentage() {
        val progress = 24f
        val indicator = ProgressIndicator.Circular(
            value = progress,
            showProgressPercentage = true
        )
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_CIRCULAR, progress).assertExists()
        rule.onNodeWithTag(TestTags.STATE_LOADING_LABEL_PERCENTAGE).assertExists()
    }

    @Test
    fun stateViewDisplaysLoadingWithCustomDeterminateCircularIndicator() {
        var customViewCalled = false
        var customViewProgress: Float? = null
        val progress = 24f
        val indicator = ProgressIndicator.Circular(
            value = progress,
            customProgressIndicator = { currentProgress ->
                customViewCalled = true
                customViewProgress = currentProgress
            }
        )
        val state = State.Loading(indicator = indicator)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTags(TestTags.STATE_LOADING_CIRCULAR, progress)
            .assertDoesNotExist()
        assert(customViewCalled)
        assert(customViewProgress == progress)
    }

    @Test
    fun stateViewDisplaysFailure() {
        val state = State.Failure()
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_FAILURE).assertExists()
    }

    @Test
    fun stateViewDisplaysFailureWithLabel() {
        val labelText = "Oh nahh"
        val state = State.Failure(labelText)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).apply {
            assertExists()
            assertIsDisplayed()
            assertTextContains(labelText)
        }
    }

    @Test
    fun stateViewDisplaysFailureWithNoLabel() {
        val state = State.Failure()
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).assertDoesNotExist()
    }

    @Test
    fun stateViewDisplaysCustomFailure() {
        var customViewCalled = false
        val state = State.Failure { customViewCalled = true }
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_FAILURE).assertDoesNotExist()
        assert(customViewCalled)
    }

    @Test
    fun stateViewDisplaysSuccess() {
        val state = State.Success()
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_SUCCESS).assertExists()
    }

    @Test
    fun stateViewDisplaysSuccessWithLabel() {
        val labelText = "Oh yeahhh!"
        val state = State.Success(labelText)
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).apply {
            assertExists()
            assertIsDisplayed()
            assertTextContains(labelText)
        }
    }

    @Test
    fun stateViewDisplaysSuccessWithNoLabel() {
        val state = State.Success()
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_VIEW_LABEL_TEXT).assertDoesNotExist()
    }

    @Test
    fun stateViewDisplaysCustomSuccess() {
        var customViewCalled = false
        val state = State.Success { customViewCalled = true }
        rule.setContent { StateView(config = StateConfig(state)) }
        rule.onNodeWithTag(TestTags.STATE_SUCCESS).assertDoesNotExist()
        assert(customViewCalled)
    }

}