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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cabin
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton

@Composable
internal fun CoreSample1(closeSelection: () -> Unit) {

    CoreDialog(
        show = true,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                "nahhh",
                IconSource(Icons.Rounded.Notifications),
                ButtonStyle.FILLED
            ),
            positiveButton = SelectionButton(
                "yaah",
                IconSource(Icons.Rounded.Cabin),
                ButtonStyle.ELEVATED
            ),
        ),
        onPositiveValid = true,
        body = {
            Text(text = "Test")
        },
        onClose = {
            closeSelection()
        }
    )
}