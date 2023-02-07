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
@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.maxkeppeler.sheets.state.models

import androidx.compose.runtime.Composable

/**
 * Defined states. While failure, success and custom are stationary screens,
 * the loading state can be updated dynamically to reflect progress.
 * @param customView Use for a custom composable view instead of the default state view
 * @param preView A custom composable view that is added above the indicator view
 * @param postView A custom composable view that is added below the indicator view
 * @param labelText The text for a label that is displayed at the top of the state view
 */
sealed class State(
    val customView: (@Composable () -> Unit)? = null,
    val preView: (@Composable () -> Unit)? = null,
    val postView: (@Composable () -> Unit)? = null,
    val labelText: String? = null
) {

    /**
     * Loading state.
     */
    class Loading : State {

        internal var indicator: ProgressIndicator? = null

        /**
         * Display a success state.
         * @param labelText The text for a label that is displayed at the top of the state view
         */
        constructor(
            labelText: String? = null,
            indicator: ProgressIndicator
        ) : super(
            labelText = labelText
        ) {
            this.indicator = indicator
        }

        /**
         * Display a success state.
         * @param labelText The text for a label that is displayed at the top of the state view
         * @param preView A custom composable view that is added above the indicator view
         * @param postView A custom composable view that is added below the indicator view
         */
        constructor(
            labelText: String? = null,
            indicator: ProgressIndicator,
            preView: (@Composable () -> Unit)? = null,
            postView: (@Composable () -> Unit)? = null
        ) : super(
            labelText = labelText,
            preView = preView,
            postView = postView
        ) {
            this.indicator = indicator
        }

        /**
         * Display a loading state.
         * @param customView Use for a custom composable view instead of the default state view
         */
        constructor(customView: @Composable () -> Unit) : super(customView = customView)
    }

    /**
     * Failure state.
     */
    class Failure : State {

        /**
         * Display a failure state.
         */
        constructor() : super()

        /**
         * Display a failure state.
         * @param labelText The text for a label that is displayed at the top of the state view
         * @param preView A custom composable view that is added above the indicator view
         * @param postView A custom composable view that is added below the indicator view
         */
        constructor(
            labelText: String? = null,
            preView: (@Composable () -> Unit)? = null,
            postView: (@Composable () -> Unit)? = null
        ) : super(
            labelText = labelText,
            preView = preView,
            postView = postView
        )

        /**
         * Display a failure state.
         * @param customView Use for a custom composable view instead of the default state view
         */
        constructor(customView: @Composable () -> Unit) : super(customView = customView)
    }

    /**
     * Success state.
     */
    class Success : State {

        /**
         * Display a success state.
         */
        constructor() : super()

        /**
         * Display a success state.
         * @param labelText The text for a label that is displayed at the top of the state view
         * @param preView A custom composable view that is added above the indicator view
         * @param postView A custom composable view that is added below the indicator view
         */
        constructor(
            labelText: String? = null,
            preView: (@Composable () -> Unit)? = null,
            postView: (@Composable () -> Unit)? = null
        ) : super(
            labelText = labelText,
            preView = preView,
            postView = postView
        )

        /**
         * Display a success state.
         * @param customView Use for a custom composable view instead of the default state view
         */
        constructor(customView: @Composable () -> Unit) : super(customView = customView)
    }
}
