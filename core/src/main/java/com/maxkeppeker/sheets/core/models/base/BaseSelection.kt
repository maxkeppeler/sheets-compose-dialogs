package com.maxkeppeker.sheets.core.models.base

abstract class BaseSelection {
    open val withButtonView: Boolean = true
    abstract val negativeButtonText: String?
    abstract val positiveButtonText: String?
    abstract val onNegativeClick: (() -> Unit)?
}