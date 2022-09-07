package com.maxkeppeler.sheets.option.models

import androidx.compose.runtime.Composable

/**
 * Details information for an option.
 * @param title The text that is used in the title.
 * @param body The text that is used in the body.
 * @param postView The content that can be added after the body.
 */
class OptionDetails(
    val title: String,
    val body: String,
    val postView: (@Composable (selected: Boolean) -> Unit)? = null,
)