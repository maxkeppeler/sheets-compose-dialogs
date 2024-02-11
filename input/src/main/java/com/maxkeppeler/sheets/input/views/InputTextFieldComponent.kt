/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.input.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.input.models.Input
import com.maxkeppeler.sheets.input.models.InputTextField
import com.maxkeppeler.sheets.input.models.InputTextFieldType

/**
 * TextField component.
 * @param index The index of the input relative to all inputs.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputTextFieldComponent(
    index: Int,
    input: InputTextField,
    onInputUpdated: (Input) -> Unit
) {
    var text by remember { mutableStateOf(input.value) }

    LaunchedEffect(text) {
        onInputUpdated(input.apply {
            value = text
        })
    }

    Row(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_TEXT_FIELD, index)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldErrorContainer(
            isError = input.validationResult?.valid == false,
            errorMessage = input.validationResult?.errorMessage ?: ""
        ) {
            when (input.type) {
                InputTextFieldType.OUTLINED -> {
                    OutlinedTextField(
                        modifier = Modifier.testTags(
                            TestTags.INPUT_ITEM_TEXT_FIELD_TYPE,
                            InputTextFieldType.OUTLINED
                        ),
                        value = text ?: "",
                        onValueChange = { text = it },
                        textStyle = input.textStyle ?: LocalTextStyle.current,
                        visualTransformation = input.visualTransformation,
                        keyboardOptions = input.keyboardOptions,
                        keyboardActions = input.keyboardActions,
                        singleLine = input.singleLine,
                        maxLines = input.maxLines,
                        shape = input.shape ?: TextFieldDefaults.outlinedShape,
                    )
                }
                InputTextFieldType.DEFAULT -> {
                    TextField(
                        modifier = Modifier.testTags(
                            TestTags.INPUT_ITEM_TEXT_FIELD_TYPE,
                            InputTextFieldType.DEFAULT
                        ),
                        value = text ?: "",
                        onValueChange = { text = it },
                        textStyle = input.textStyle ?: LocalTextStyle.current,
                        visualTransformation = input.visualTransformation,
                        keyboardOptions = input.keyboardOptions,
                        keyboardActions = input.keyboardActions,
                        singleLine = input.singleLine,
                        maxLines = input.maxLines,
                        shape = input.shape ?: TextFieldDefaults.outlinedShape,
                    )
                }
            }
        }
    }
}