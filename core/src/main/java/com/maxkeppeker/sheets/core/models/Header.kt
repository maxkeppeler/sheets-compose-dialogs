package com.maxkeppeker.sheets.core.models

import androidx.compose.runtime.Composable

/**
 * Available calendar configurations.
 */
abstract class Header {

    data class Default(
        val titleText: String,
        val subtitleText: String? = null,
        val icon: ImageSource? = null
    ) : Header()

    data class Custom(
        val header: @Composable () -> Unit
    ) : Header()
}