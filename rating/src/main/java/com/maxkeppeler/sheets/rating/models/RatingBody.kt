package com.maxkeppeler.sheets.rating.models

import androidx.compose.runtime.Composable

/**
 * Defined implementations of a body for the rating dialog.
 */
abstract class RatingBody {

    /**
     * Standard implementation of a body.
     * @param bodyText Text that will set as the title
     * @param preBody Content that is added before the body text.
     * @param postBody Content that is added after the body text.
     */
    data class Default(
        val bodyText: String,
        val preBody: @Composable () -> Unit = {},
        val postBody: @Composable () -> Unit = {}
    ) : RatingBody()

    /**
     * Custom implementation of a body.
     */
    data class Custom(
        val body: @Composable () -> Unit
    ) : RatingBody()
}