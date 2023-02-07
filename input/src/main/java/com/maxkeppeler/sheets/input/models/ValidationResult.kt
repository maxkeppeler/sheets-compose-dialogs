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

package com.maxkeppeler.sheets.input.models

/**
 * Result of the validation success or failure of the validation. At failure, an error text can be displayed.
 */

/**
 * A result object for input validation.
 */
sealed class ValidationResult(
    internal val valid: Boolean = true,
    internal open val errorMessage: String? = null
) {
    /**
     * Input is valid.
     */
    object Valid : ValidationResult()

    /**
     * Input is not valid.
     * @param errorMessage The reason why the input is not valid.
     */
    class Invalid(override val errorMessage: String) : ValidationResult(false)
}


