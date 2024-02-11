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
package com.maxkeppeler.sheets.rating.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.maxkeppeler.sheets.rating.models.FeedbackTextFieldType
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingViewStyle

/**
 * The rating component.
 */
@Composable
internal fun RatingComponent(
    rating: Int?,
    feedback: String?,
    feedbackError: Boolean,
    config: RatingConfig,
    onSelectRating: (Int) -> Unit,
    onUpdateFeedback: (String) -> Unit,
) {
    RatingSelectionView(
        value = rating,
        config = config,
        onSelectRating = onSelectRating,
    )
    RatingFeedbackView(
        value = feedback,
        isError = feedbackError,
        config = config,
        onUpdateFeedback = onUpdateFeedback,
    )
}

class RatingConfigProvider : PreviewParameterProvider<RatingConfig> {
    override val values: Sequence<RatingConfig> = sequenceOf(
        RatingConfig(
            ratingViewStyle = RatingViewStyle.CENTER,
            feedbackOptional = true,
            ratingOptionsCount = 5,
            feedbackTextFieldType = FeedbackTextFieldType.OUTLINED,
        ),
        RatingConfig(
            ratingViewStyle = RatingViewStyle.START,
            feedbackOptional = true,
            ratingOptionsCount = 5,
            feedbackTextFieldType = FeedbackTextFieldType.OUTLINED,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun RatingComponentPreview(
    @PreviewParameter(RatingConfigProvider::class) config: RatingConfig
) {
    Column {
        RatingComponent(
            config = config,
            rating = 3,
            feedback = "Some text",
            onSelectRating = {},
            feedbackError = false,
            onUpdateFeedback = {}
        )
    }
}