package com.maxkeppeker.sheets.core.models.base

/**
 * An icon from various sources alongside an optional contentDescription and tint.
 * @param text Text used for the button
 * @param icon Icon used for the button, or none if null
 * @param type Style used for the button
 */
data class SelectionButton(
    val text: String,
    val icon: IconSource? = null,
    val type: ButtonStyle = ButtonStyle.TEXT
)
