package com.maxkeppeler.sheets.option.models

import androidx.compose.runtime.Composable

/**
 *
 */
class OptionDetails(

    /**
     *
     */
    val title: String,

    /**
     *
     */
    val body: String,

    /**
     *
     */
    val postView: (@Composable (selected: Boolean) -> Unit)? = null,

)