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
package com.maxkeppeler.sheets.rating.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.rating.R
import com.maxkeppeler.sheets.rating.models.FeedbackTextFieldType
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingViewStyle
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view for the feedback text field.
 * @param value The current feedback value.
 * @param config The configuration for the rating view.
 * @param onUpdateFeedback Listener that is invoked when the feedback changes.
 */
@Composable
internal fun RatingFeedbackView(
    value: String? = null,
    isError: Boolean,
    config: RatingConfig,
    onUpdateFeedback: (String) -> Unit = {},
) {
    if (!config.withFeedback) return
    var text by remember { mutableStateOf(value ?: "") }
    val alignment = when (config.ratingViewStyle) {
        RatingViewStyle.START -> Arrangement.Start
        RatingViewStyle.CENTER -> Arrangement.Center
    }
    Spacer(modifier = Modifier.height(dimensionResource(RC.dimen.scd_normal_150)))
    Row(
        modifier = Modifier
            .testTags(TestTags.RATING_FEEDBACK_TEXT_FIELD)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = alignment
    ) {
        val labelText = stringResource(R.string.scd_rating_dialog_feedback_label) +
                " (" + stringResource(if (config.feedbackOptional) R.string.scd_rating_dialog_optional else R.string.scd_rating_dialog_required).lowercase() + ")"
        val defaultErrorMessage = stringResource(R.string.scd_rating_dialog)
        FeedbackTextFieldErrorContainer(
            config = config,
            isError = value != null && isError,
            errorMessage = config.feedbackErrorMessage ?: defaultErrorMessage,
        ) {
            when (config.feedbackTextFieldType) {
                FeedbackTextFieldType.OUTLINED -> {
                    OutlinedTextField(
                        modifier = Modifier.testTags(
                            TestTags.RATING_FEEDBACK_TEXT_FIELD_TYPE,
                            FeedbackTextFieldType.OUTLINED
                        ),
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                            onUpdateFeedback(newText)
                        },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = {
                            Text(
                                text = labelText,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                    )
                }

                FeedbackTextFieldType.DEFAULT -> {
                    TextField(
                        modifier = Modifier.testTags(
                            TestTags.RATING_FEEDBACK_TEXT_FIELD_TYPE,
                            FeedbackTextFieldType.DEFAULT
                        ),
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                            onUpdateFeedback(newText)
                        },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = {
                            Text(
                                text = labelText,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RatingFeedbackViewPreview() {
    RatingFeedbackView(
        config = RatingConfig(),
        isError = false
    )
}