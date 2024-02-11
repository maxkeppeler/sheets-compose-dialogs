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
package com.maxkeppeler.sheets.rating.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.utils.BaseConstants.DEFAULT_ICON_STYLE
import com.maxkeppeler.sheets.rating.utils.Constants.DEFAULT_RATING_OPTIONS_COUNT

/**
 * The general configuration for the rating dialog.
 */
class RatingConfig(
    val ratingViewStyle: RatingViewStyle = RatingViewStyle.CENTER,
    val feedbackTextFieldType: FeedbackTextFieldType = FeedbackTextFieldType.DEFAULT,
    val feedbackErrorMessage: String? = null,
    val withFeedback: Boolean = false,
    val feedbackOptional: Boolean = true,
    @IntRange(from = 3, to = 10) val ratingOptionsCount: Int = DEFAULT_RATING_OPTIONS_COUNT,
    override val icons: LibIcons = DEFAULT_ICON_STYLE,
) : BaseConfigs()


enum class RatingViewStyle {
    START,
    CENTER,
}