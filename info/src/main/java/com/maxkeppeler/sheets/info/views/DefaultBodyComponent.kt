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
package com.maxkeppeler.sheets.info.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.info.models.InfoBody
import com.maxkeppeler.sheets.core.R as RC

/**
 * The default body component for the info dialog.
 * @param body The data of the default body.
 */
@Composable
internal fun DefaultBodyComponent(body: InfoBody.Default) {
    Column(
        modifier = Modifier
            .testTag(TestTags.INFO_BODY_DEFAULT)
            .padding(top = dimensionResource(id = RC.dimen.scd_normal_100))
    ) {
        body.preBody()

        Text(
            modifier = Modifier.testTag(TestTags.INFO_BODY_DEFAULT_TEXT),
            text = body.bodyText,
            style = MaterialTheme.typography.bodyMedium
        )

        body.postBody()
    }
}