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

package com.maxkeppeler.sheets.rating

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.rating.models.RatingBody
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingSelection
import com.maxkeppeler.sheets.rating.views.RatingBodyComponent
import com.maxkeppeler.sheets.rating.views.RatingComponent

/**
 * Rating view for the use-case to display a rating view.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The configuration for the rating view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun RatingView(
    useCaseState: UseCaseState,
    selection: RatingSelection,
    config: RatingConfig,
    header: Header? = null,
    body: RatingBody,
) {
    val ratingState = rememberRatingState(selection, config)
    StateHandler(useCaseState, ratingState)
    val coroutine = rememberCoroutineScope()
    val onSelectRating: (Int) -> Unit = {
        ratingState.updateRating(it)
        if (!config.withFeedback) {
            BaseBehaviors.autoFinish(
                selection = selection,
                coroutine = coroutine,
                onSelection = ratingState::onFinish,
                onFinished = useCaseState::finish,
                onDisableInput = ratingState::disableInput
            )
        }
    }
    FrameBase(
        header = header,
        layout = {
            RatingBodyComponent(
                config = config,
                body = body
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.scd_normal_150)))
            RatingComponent(
                config = config,
                rating = ratingState.rating,
                feedback = ratingState.feedback,
                feedbackError = !ratingState.feedbackValid,
                onSelectRating = onSelectRating,
                onUpdateFeedback = ratingState::updateFeedback,
            )
        },
        buttonsVisible = selection.withButtonView
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            selection = selection,
            onPositiveValid = ratingState.ratingValid && ratingState.feedbackValid,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = ratingState::onFinish,
            state = useCaseState,
        )
    }
}
