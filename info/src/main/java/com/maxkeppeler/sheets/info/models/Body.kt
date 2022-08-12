package com.maxkeppeler.sheets.info.models

import androidx.compose.runtime.Composable

/**
 * Available calendar configurations.
 */
abstract class Body {

    data class Default(
        val bodyText: String,
        val preBody: @Composable () -> Unit = {},
        val postBody: @Composable () -> Unit = {}
    ) : Body()

    data class Custom(
        val body: @Composable () -> Unit
    ) : Body()
}