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

package com.maxkeppeler.sheets.rating.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.rating.RatingView
import com.maxkeppeler.sheets.rating.models.FeedbackTextFieldType
import com.maxkeppeler.sheets.rating.models.RatingBody
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalMaterial3Api::class)
@RunWith(AndroidJUnit4::class)
class RatingViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun givenRatingView_whenDefaultBodyDisplayed_thenBodyExists() {
        val bodyText = "Rate our service"
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(),
                body = RatingBody.Default(bodyText)
            )
        }
        rule.onNodeWithTag(TestTags.RATING_BODY_DEFAULT).assertExists()
    }

    @Test
    fun givenRatingView_whenDefaultBodyDisplayed_thenBodyExistsWithText() {
        val text = "This is a default body."
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(),
                body = RatingBody.Default(text)
            )
        }
        rule.onNodeWithTag(TestTags.RATING_BODY_DEFAULT_TEXT).apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun givenRatingView_whenCustomBodyUsed_thenCustomBodyRendered() {
        var customBodyCalled = false
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(),
                body = RatingBody.Custom { customBodyCalled = true }
            )
        }
        assert(customBodyCalled)
    }

    @Test
    fun givenRatingView_whenRatingSelected_thenFeedbackAndRatingSetCorrectly() {
        val expectedRating = 4
        val expectedFeedback = "Great service"
        var actualRating: Int? = null
        var actualFeedback: String? = null

        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection(
                    onSelectRating = { rating, feedback ->
                        actualRating = rating
                        actualFeedback = feedback
                    }
                ),
                config = RatingConfig(
                    withFeedback = true,
                ),
                body = RatingBody.Default("Please rate us")
            )
        }
        rule.onNodeWithTags(TestTags.RATING_STAR_INPUT, expectedRating).performClick()
        rule.onNodeWithTags(TestTags.RATING_FEEDBACK_TEXT_FIELD_TYPE, FeedbackTextFieldType.DEFAULT)
            .performTextInput(expectedFeedback)
        rule.onPositiveButton().performClick()

        assert(actualRating == expectedRating)
        assert(actualFeedback == expectedFeedback)
    }

    @Test
    fun givenRatingView_withFeedbackEnabled_thenFeedbackFieldExists() {
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(withFeedback = true),
                body = RatingBody.Default("Feedback Enabled Test")
            )
        }
        rule.onNodeWithTag(TestTags.RATING_FEEDBACK_TEXT_FIELD).assertExists()
    }

    @Test
    fun givenRatingView_withFeedbackDisabled_thenFeedbackFieldNotExists() {
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(withFeedback = false),
                body = RatingBody.Default("Feedback Disabled Test")
            )
        }
        rule.onNodeWithTag(TestTags.RATING_FEEDBACK_TEXT_FIELD).assertDoesNotExist()
    }

    @Test
    fun givenRatingView_withOptionalFeedback_thenFeedbackCanBeSkipped() {
        val expectedRating = 3
        var actualRating: Int? = null
        var actualFeedback: String? = null
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { rating, feedback ->
                    actualRating = rating
                    actualFeedback = feedback
                },
                config = RatingConfig(withFeedback = true, feedbackOptional = true),
                body = RatingBody.Default("Optional Feedback Test")
            )
        }
        rule.onNodeWithTags(TestTags.RATING_STAR_INPUT, expectedRating).performClick()
        rule.onPositiveButton().assertIsEnabled()
        rule.onPositiveButton().performClick()

        assert(actualRating == expectedRating)
        assert(actualFeedback.isNullOrBlank())
    }

    @Test
    fun givenRatingView_withCustomMinStarCount_thenCorrectNumberOfStarsDisplayed() {
        val starCount = 3
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(ratingOptionsCount = starCount),
                body = RatingBody.Default("Star Count Test")
            )
        }
        for (i in 1..starCount) {
            rule.onNodeWithTags(TestTags.RATING_STAR_INPUT, i).assertExists()
        }
    }

    @Test
    fun givenRatingView_withCustomMaxStarCount_thenCorrectNumberOfStarsDisplayed() {
        val starCount = 8
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(ratingOptionsCount = starCount),
                body = RatingBody.Default("Star Count Test")
            )
        }
        for (i in 1..starCount) {
            rule.onNodeWithTags(TestTags.RATING_STAR_INPUT, i).assertExists()
        }
    }

    @Test
    fun givenRatingView_withCustomStarCount_thenCorrectNumberOfStarsDisplayed() {
        val starCount = 6
        rule.setContent {
            RatingView(
                useCaseState = UseCaseState(visible = true),
                selection = RatingSelection { _, _ -> },
                config = RatingConfig(ratingOptionsCount = starCount),
                body = RatingBody.Default("Star Count Test")
            )
        }
        for (i in 1..starCount) {
            rule.onNodeWithTags(TestTags.RATING_STAR_INPUT, i).assertExists()
        }
    }
}