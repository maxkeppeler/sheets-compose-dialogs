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
package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.core.R as RC


/**
 * The textual header item component.
 * @param name The name of the category.
 * @param selected If the current category is selected.
 * @param index Index of category item.
 * @param onClick The listener that is invoked when this component is clicked.
 */
@Composable
internal fun EmojiTextHeaderItemComponent(
    name: String,
    selected: Boolean,
    index: Int,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .testTags(TestTags.EMOJI_CATEGORY_TAB, index)
            .clip(RoundedCornerShape(50))
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable { onClick() }
            .padding(
                horizontal = dimensionResource(RC.dimen.scd_small_150),
                vertical = dimensionResource(RC.dimen.scd_small_50)
            ),
        text = name,
        style = if (selected) MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
        else MaterialTheme.typography.labelLarge
    )
}