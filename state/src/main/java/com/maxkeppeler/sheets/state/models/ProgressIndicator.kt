@file:Suppress("unused")

package com.maxkeppeler.sheets.state.models

import androidx.compose.runtime.Composable

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