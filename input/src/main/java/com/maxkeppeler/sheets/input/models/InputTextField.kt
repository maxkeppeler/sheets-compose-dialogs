/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@file:Suppress("unused")
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.input.models

import android.os.Bundle
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Represents a text field.
 * @param text The input text to be shown in the text field
 * @param type The style of the text field.
 * @param changeListener The listener that returns the changed value.
 * @param validationListener The listener that is invoked to check if the value is valid.
 * @param resultListener The listener that returns the final value.
 * @param textStyle the style to be applied to the input text. Defaults to LocalTextStyle.
 * @param visualTransformation Transforms the visual representation of the input value For example, you can use PasswordVisualTransformation to create a password text field. By default, no visual transformation is applied.
 * @param keyboardOptions Software keyboard options that contains configuration such as KeyboardType and ImeAction
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in KeyboardOptions.imeAction
 * @param singleLine When true, this text field becomes a single horizontally scrolling text field instead of wrapping onto multiple lines. The keyboard will be informed to not show the return key as the ImeAction. Note that maxLines parameter will be ignored as the maxLines attribute will be automatically set to 1.
 * @param maxLines The maximum height in terms of maximum number of visible lines. Should be equal or greater than 1. Note that this parameter will be ignored and instead maxLines will be set to 1 if singleLine is set to true.
 * @param shape Defines the shape of this text field's border.
 * @param colors TextFieldColors that will be used to resolve the colors used for this text field in different states.
 * @param key The key of the input that is used for saving the data in the result bundle. (Alternatively the index of the input is used.)
 * @param required If the input is required to finish the dialog.
 * @param header The additional [InputHeader] to add more context information to the selection.
 * @param columns The columns that this input spans.
 */
data class InputTextField(
    internal val text: String? = null,
    internal val type: InputTextFieldType = InputTextFieldType.DEFAULT,
    private val changeListener: ((String?) -> Unit)? = null,
    private val validationListener: ((String?) -> ValidationResult)? = null,
    internal val resultListener: ((String?) -> Unit)? = null,
    internal val textStyle: TextStyle? = null,
    internal val visualTransformation: VisualTransformation = VisualTransformation.None,
    internal val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    internal val keyboardActions: KeyboardActions = KeyboardActions.Default,
    internal val singleLine: Boolean = false,
    internal val maxLines: Int = Int.MAX_VALUE,
    internal val shape: Shape? = null,
    internal val colors: TextFieldColors? = null,
    override val key: String? = null,
    override val required: Boolean = false,
    override val header: InputHeader? = null,
    override val columns: Int? = null,
) : Input() {

    internal var value: String? = text
        set(value) {
            if (field != value) {
                field = value
                onChange()
            }
            valid = isValid()
        }

    internal var validationResult: ValidationResult? = null
        get() = if (text == value) null else field

    override fun onChange() = changeListener?.invoke(value!!)

    override fun onResult() = resultListener?.invoke(value!!)

    override fun isValid(): Boolean {
        validationResult = validationListener?.invoke(value)
        val customValidationOk = validationResult?.valid ?: true
        val requiredValid = (required && value?.isNotEmpty() == true || !required)
        return customValidationOk && requiredValid
    }

    override fun putValue(bundle: Bundle) {
        bundle.putString(getBundleKey(), value)
    }
}