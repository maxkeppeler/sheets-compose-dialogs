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
package com.maxkeppeker.sheets.core.views.base

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.utils.BaseValues

/**
 * Base component for the content structure of a dialog.
 * @param header The content to be displayed inside the dialog that functions as the header view of the dialog.
 * @param contentHorizontalAlignment The horizontal alignment of the layout's children.
 * @param contentPaddingValues The padding that is applied to the content.
 * @param content The content to be displayed inside the dialog between the header and the buttons.
 * @param buttonsVisible Display the buttons.
 * @param buttons The content to be displayed inside the dialog that functions as the buttons view of the dialog.
 */
@Composable
fun FrameBase(
    header: @Composable ColumnScope.() -> Unit,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    contentPaddingValues: PaddingValues = BaseValues.CONTENT_DEFAULT_PADDING,
    content: @Composable ColumnScope.() -> Unit,
    buttonsVisible: Boolean = true,
    buttons: @Composable (ColumnScope.() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        header()
        Column(
            modifier = Modifier
                .padding(contentPaddingValues)
                .fillMaxWidth(),
            horizontalAlignment = contentHorizontalAlignment,
            content = content
        )

        buttons?.let { buttons ->
            if (buttonsVisible) buttons.invoke(this)
        }
    }
}