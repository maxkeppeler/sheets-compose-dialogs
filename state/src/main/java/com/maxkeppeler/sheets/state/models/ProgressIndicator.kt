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
@file:Suppress("unused")

package com.maxkeppeler.sheets.state.models

import androidx.compose.runtime.Composable

/**
 * Defined implementations of a progress indicator.
 * @param value The progress of the indicator.
 * @param showProgressPercentage Show the progress as percentage.
 * @param customProgressIndicator Custom view that replaces the default progress indicator view.
 * @param customIndicator Custom view that replaces the default indicator view.
 */
sealed class ProgressIndicator(
    open var value: Float? = null,
    open val showProgressPercentage: Boolean = false,
    open val customProgressIndicator: @Composable ((Float) -> Unit)? = null,
    open val customIndicator: @Composable (() -> Unit)? = null,
) {

    /**
     * Linear progress indicator.
     */
    class Linear : ProgressIndicator {

        /**
         * Indeterminate linear progress indicator.
         * @param customIndicator Use for a custom composable indicator instead of the default one
         */
        constructor(
            customIndicator: @Composable (() -> Unit)? = null
        ) : super(
            customIndicator = customIndicator
        )

        /**
         * Determinate linear progress indicator.
         * @param value The progress of this progress indicator, where 0.0 represents no progress and 1.0
         * @param showProgressPercentage Display a label that reflects the progress in a percentage
         * @param customProgressIndicator Use for a custom composable indicator instead of the default one
         */
        constructor(
            value: Float,
            showProgressPercentage: Boolean = false,
            customProgressIndicator: @Composable ((Float) -> Unit)? = null
        ) : super(
            value = value,
            showProgressPercentage = showProgressPercentage,
            customProgressIndicator = customProgressIndicator
        )
    }

    /**
     * Circular progress indicator.
     */
    class Circular : ProgressIndicator {

        /**
         * Indeterminate circular progress indicator.
         * @param customIndicator Use for a custom composable indicator instead of the default one
         */
        constructor(
            customIndicator: @Composable (() -> Unit)? = null
        ) : super(
            customIndicator = customIndicator
        )

        /**
         * Determinate circular progress indicator.
         * @param value The progress of this progress indicator, where 0.0 represents no progress and 1.0
         * @param showProgressPercentage Display a label that reflects the progress in a percentage
         * @param customProgressIndicator Use for a custom composable indicator instead of the default one
         */
        constructor(
            value: Float,
            showProgressPercentage: Boolean = false,
            customProgressIndicator: @Composable ((Float) -> Unit)? = null
        ) : super(
            value = value,
            showProgressPercentage = showProgressPercentage,
            customProgressIndicator = customProgressIndicator
        )
    }

}