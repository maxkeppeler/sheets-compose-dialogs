package com.maxkeppeker.sheets.core.models.base

import androidx.compose.runtime.Composable

/**
 * Defined implementations of a header for the dialogs.
 */
abstract class Header {

    /**
     * Standard implementation of a header.
     * @param icon The icon that is displayed above the title..
     * @param title The text that will be set as title.
     */
    data class Default(
        val title: String,
        val icon: IconSource? = null,
    ) : Header()

    /**
     * Custom implementation of a header.
     */
    data class Custom(
        val header: @Composable () -> Unit
    ) : Header()
}