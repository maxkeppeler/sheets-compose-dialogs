package com.maxkeppeker.sheets.core.models.base

abstract class BaseSelection {
    open val withButtonView: Boolean = true
    open val negativeButtonText: String? = null
    open val positiveButtonText: String? = null
    open val onNegativeClick: (() -> Unit)? = null
}