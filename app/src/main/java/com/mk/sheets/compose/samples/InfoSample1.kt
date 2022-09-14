/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
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

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection

@Composable
internal fun InfoSample1(closeSelection: () -> Unit) {

    InfoDialog(
        show = true,
        header = Header.Default(
            titleText = "Do you want to work?",
            subtitleText = "It's essential"
        ),
        body = Body.Default(
            bodyText = "this is a very long text bla bla",
            postBody = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Test")
                }
            }
        ),
        selection = InfoSelection(onPositiveClick = {}, onNegativeClick = {}),
        onClose = { closeSelection() }
    )
}