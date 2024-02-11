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
@file:Suppress("unused")

package com.maxkeppeler.sheets.option.models

import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.IconSource
import java.io.Serializable
import java.util.*

/**
 * An option for the the option dialog.
 * @param icon The icon displayed in the option.
 * @param titleText The title displayed in the option.
 * @param subtitleText The subtitle displayed in the option.
 * @param selected The state if the option is selected by default.
 * @param disabled The state if the option is disabled permanently.
 * @param details Define further information with longer texts. The option will be marked with an Info-Icon and the user can open a dialog to the option where the longer texts are displayed.
 * @param onLongClick The listener that is invoked when the option recognizes a long click.
 * @param customView Replace the standard grid or list item view with a custom view.
 * @param listTopView Add a content above the option list item.
 * @param listBottomView Add a content below the option list item.
 */
data class Option(
    val icon: IconSource? = null,
    val titleText: String,
    val subtitleText: String? = null,
    val selected: Boolean = false,
    val disabled: Boolean = false,
    val details: OptionDetails? = null,
    val onLongClick: (() -> Unit)? = null,
    val customView: (@Composable (selected: Boolean) -> Unit)? = null,
    val listTopView: (@Composable (selected: Boolean) -> Unit)? = null,
    val listBottomView: (@Composable (selected: Boolean) -> Unit)? = null,
) : Serializable {
    internal var position: Int = 0
}

