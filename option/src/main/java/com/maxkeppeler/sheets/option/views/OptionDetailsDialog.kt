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
package com.maxkeppeler.sheets.option.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.core.R as RC

/**
 * Option dialog for the use-case to display a list or grid of options.
 * @param state The state of the sheet.
 * @param option The option that will be displayed.
 * @param backgroundColor The color that is used for the background of the option.
 * @param iconColor The color that is used for the icon of the option.
 * @param textColor The color that is used for the texts of the option.
 */
@Composable
internal fun OptionDetailsDialog(
    state: SheetState,
    option: Option,
    backgroundColor: Color,
    iconColor: Color,
    textColor: Color
) {
    DialogBase(
        state = state,
        onDialogClick = { state.hide() }) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(RC.dimen.scd_normal_100))
                    .padding(horizontal = dimensionResource(RC.dimen.scd_normal_100))
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .then(
                        if (option.disabled || option.selected) Modifier.background(
                            backgroundColor
                        ) else Modifier
                    )
                    .padding(dimensionResource(RC.dimen.scd_normal_100)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                option.icon?.let {
                    IconComponent(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        iconSource = it,
                        tint = iconColor
                    )
                }
                Text(
                    modifier = Modifier.padding(start = if (option.icon != null) 16.dp else 0.dp),
                    text = option.titleText,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(RC.dimen.scd_normal_150))
                    .padding(bottom = dimensionResource(RC.dimen.scd_normal_150))
            ) {
                Text(
                    modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_normal_100)),
                    text = option.details!!.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_small_100)),
                    text = option.details.body,
                    style = MaterialTheme.typography.bodyMedium
                )
                option.details.postView?.invoke(option.selected)
            }
        }
    }
}