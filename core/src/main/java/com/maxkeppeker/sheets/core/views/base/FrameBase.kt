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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core.views.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseValues
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.core.R as RC

/**
 * Base component for the content structure of a dialog.
 * @param header The content to be displayed inside the dialog that functions as the header view of the dialog.
 * @param contentHorizontalAlignment The horizontal alignment of the layout's children.
 * @param horizontalContentPadding The horizontal padding that is applied to the content.
 * @param content The content to be displayed inside the dialog between the header and the buttons.
 * @param buttonsVisible Display the buttons.
 * @param buttons The content to be displayed inside the dialog that functions as the buttons view of the dialog.
 */
@Composable
fun FrameBase(
    header: Header? = null, //, @Composable ColumnScope.() -> Unit,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    horizontalContentPadding: PaddingValues = BaseValues.CONTENT_DEFAULT_PADDING,
    content: @Composable ColumnScope.() -> Unit,
    buttonsVisible: Boolean = true,

    buttons: @Composable (ColumnScope.() -> Unit)? = null,
) {
    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier.wrapContentHeight()
    ) {

        header?.let {
            // Display header
            HeaderComponent(
                header = header,
                contentHorizontalPadding = PaddingValues(
                    start = horizontalContentPadding.calculateStartPadding(layoutDirection),
                    end = horizontalContentPadding.calculateEndPadding(layoutDirection),
                )
            )
        } ?: run {
            // If no header is defined, add extra spacing to the content top padding
            Spacer(Modifier.height(dimensionResource(RC.dimen.scd_small_100)))
        }

        // Spacing between content and header is usually 16dp
        Column(
            modifier = Modifier
                .padding(
                    PaddingValues(
                        start = horizontalContentPadding.calculateStartPadding(layoutDirection),
                        end = horizontalContentPadding.calculateEndPadding(layoutDirection),
                        // Enforce default top spacing
                        top = dimensionResource(RC.dimen.scd_normal_100),
                    )
                )
                .fillMaxWidth(),
            horizontalAlignment = contentHorizontalAlignment,
            content = content
        )

        buttons?.let { buttons ->
            if (buttonsVisible) buttons.invoke(this)
            else Spacer(modifier = Modifier.height(dimensionResource(RC.dimen.scd_normal_150)))
        }
    }
}