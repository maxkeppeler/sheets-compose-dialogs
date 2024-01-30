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