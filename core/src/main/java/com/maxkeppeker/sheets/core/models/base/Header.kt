package com.maxkeppeker.sheets.core.models.base

import androidx.compose.runtime.Composable

/**
 * Defined implementations of a header for the dialogs.
 */
abstract class Header {

    /**
     * Standard implementation of a header.
     * @param titleText Text that will set as the title
     * @param subtitleText Text that will set as the subtitle
     * @param icon An icon that is displayed on the left side of the title and subtitle.
     */
    data class Default(
        val titleText: String,
        val subtitleText: String? = null,
        val icon: IconSource? = null,
    ) : Header()

    /**
     * Custom implementation of a header.
     */
    data class Custom(
        val header: @Composable () -> Unit
    ) : Header()
}