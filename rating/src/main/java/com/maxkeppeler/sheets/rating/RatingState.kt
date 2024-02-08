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
package com.maxkeppeler.sheets.rating

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingSelection
import java.io.Serializable

/**
 * Handles the color state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class RatingState(
    val selection: RatingSelection,
    val config: RatingConfig = RatingConfig(),
    stateData: RatingStateData? = null,
) : BaseTypeState() {

    var rating: Int? by mutableStateOf(stateData?.rating)
    var feedback by mutableStateOf(stateData?.feedback)

    var ratingValid by mutableStateOf(isRatingValid())
    var feedbackValid by mutableStateOf(isFeedbackValid())

    private fun checkValid() {
        ratingValid = isRatingValid()
        feedbackValid = isFeedbackValid()
    }

    fun updateRating(newRating: Int) {
        rating = newRating
        checkValid()
    }

    fun updateFeedback(newFeedback: String) {
        feedback = newFeedback
        checkValid()
    }

    private fun isRatingValid(): Boolean = rating != null

    private fun isFeedbackValid(): Boolean =
        if (config.feedbackOptional) true
        else !feedback.isNullOrBlank()

    fun onFinish() {
        selection.onSelectRating(rating!!, feedback)
    }

    override fun reset() {
        rating = null
        feedback = null
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: RatingSelection,
            config: RatingConfig
        ): Saver<RatingState, *> = Saver(
            save = { state -> RatingStateData(state.rating, state.feedback) },
            restore = { data -> RatingState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the rating info of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class RatingStateData(
        val rating: Int?,
        val feedback: String?,
    ) : Serializable
}

/**
 * Create a RatingState and remember it.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberRatingState(
    selection: RatingSelection,
    config: RatingConfig,
): RatingState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = RatingState.Saver(selection, config),
    init = { RatingState(selection, config) }
)