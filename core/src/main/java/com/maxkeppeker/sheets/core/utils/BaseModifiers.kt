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
package com.maxkeppeker.sheets.core.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R


/**
 * Defines module-wide modifiers.
 */
object BaseModifiers {

    /**
     * Dynamic content in a Column is wrapped and can expand up to a specific height.
     * @receiver modifier Modifier that is used to apply the behavior
     * @param scope The current column scope
     */
    fun Modifier.dynamicContentWrapOrMaxHeight(scope: ColumnScope): Modifier = composed {
        val modifier = this
        val dynamicContentMaxHeight = dimensionResource(id = R.dimen.scd_dynamic_content_max_height)
        scope.run {
            modifier
                .weight(1f, false)
                .heightIn(max = dynamicContentMaxHeight)
        }
    }
}