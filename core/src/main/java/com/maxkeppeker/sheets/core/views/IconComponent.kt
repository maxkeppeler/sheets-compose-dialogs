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
package com.maxkeppeker.sheets.core.views

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.maxkeppeker.sheets.core.models.base.IconSource

/**
 * Icon component that is displayed in various places in a dialog.
 * @param modifier The modifier that is applied to this icon.
 * @param iconSource The icon that is used.
 * @param tint The color that is used to tint the icon.
 * @param defaultTint The default color that is used.
 */
@Composable
fun IconComponent(
    modifier: Modifier,
    iconSource: IconSource,
    tint: Color? = null,
    defaultTint: Color? = null,
) {

    val actualTint = tint ?: iconSource.tint ?: defaultTint ?: LocalContentColor.current

    val resolvedPainterDrawableRes = iconSource.drawableRes?.let { painterResource(id = it) }
    (iconSource.painter ?: resolvedPainterDrawableRes)?.let {
        Icon(
            modifier = modifier,
            painter = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

    iconSource.bitmap?.let {
        Icon(
            modifier = modifier,
            bitmap = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

    iconSource.imageVector?.let {
        Icon(
            modifier = modifier,
            imageVector = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

}