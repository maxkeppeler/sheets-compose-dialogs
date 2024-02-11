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
package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.core.R as RC

/**
 * Header component of the dialog.
 * @param header Implementation of the header.
 */
@ExperimentalMaterial3Api
@Composable
fun HeaderComponent(
    header: Header,
    contentHorizontalPadding: PaddingValues,
) {
    when (header) {
        is Header.Custom -> header.header.invoke(contentHorizontalPadding)
        is Header.Default -> DefaultHeaderComponent(header, contentHorizontalPadding)
    }
}

/**
 * The default header component for a dialog.
 * @param header The data of the default header.
 */
@Composable
private fun DefaultHeaderComponent(
    header: Header.Default,
    contentHorizontalPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .testTag(TestTags.HEADER_DEFAULT)
            .fillMaxWidth()
            .padding(contentHorizontalPadding)
            .padding(top = dimensionResource(id = RC.dimen.scd_normal_150)),
        horizontalAlignment = if (header.icon != null) Alignment.CenterHorizontally else Alignment.Start
    ) {
        header.icon?.let {
            IconComponent(
                modifier = Modifier
                    .testTag(TestTags.HEADER_DEFAULT_ICON)
                    .size(dimensionResource(RC.dimen.scd_size_150)),
                iconSource = it,
                defaultTint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = header.title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .testTag(TestTags.HEADER_DEFAULT_TEXT)
                .padding(
                    top = if (header.icon != null) dimensionResource(id = RC.dimen.scd_normal_100)
                    else 0.dp
                ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (header.icon != null) TextAlign.Center else TextAlign.Start
        )
    }
}