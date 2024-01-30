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
package com.maxkeppeler.sheets.rating.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingViewStyle
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view for the selection of the rating.
 * @param value The current rating value.
 * @param config The configuration for the rating view.
 * @param onSelectRating Listener that is invoked when the rating changes.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RatingSelectionView(
    value: Int?,
    config: RatingConfig,
    onSelectRating: (Int) -> Unit,
) {
    val alignment = when (config.ratingViewStyle) {
        RatingViewStyle.START -> Alignment.Start
        RatingViewStyle.CENTER -> Alignment.CenterHorizontally
    }
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(RC.dimen.scd_small_50),
            alignment = alignment
        ),
        verticalAlignment = Alignment.CenterVertically,
        maxItemsInEachRow = 6,
    ) {
        (1..config.ratingOptionsCount).forEach { optionIndex ->
            RatingOptionView(
                modifier = Modifier.testTags(TestTags.RATING_STAR_INPUT, optionIndex.toString()),
                config = config,
                selected = value?.let { optionIndex <= it } ?: false,
                onSelect = { onSelectRating(optionIndex) }
            )
        }
    }
}

/**
 * The rating option view.
 * @param modifier The modifier for the option.
 * @param selected If the option is selected.
 * @param config The configuration for the rating view.
 * @param onSelect Listener that is invoked when the option is selected.
 */
@Composable
private fun RatingOptionView(
    modifier: Modifier,
    selected: Boolean,
    config: RatingConfig,
    onSelect: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelect() }
    ) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(RC.dimen.scd_size_250))
                .sizeIn(
                    minWidth = dimensionResource(RC.dimen.scd_size_200),
                    minHeight = dimensionResource(RC.dimen.scd_size_200),
                    maxHeight = dimensionResource(RC.dimen.scd_size_250),
                    maxWidth = dimensionResource(RC.dimen.scd_size_250)
                ),
            imageVector = config.icons.Star,
            contentDescription = null,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@Preview
@Composable
private fun PreviewRatingSelectionView() {
    RatingSelectionView(
        value = 4,
        config = RatingConfig(ratingOptionsCount = 5),
        onSelectRating = {},
    )
}