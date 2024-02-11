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
package com.mk.sheets.compose.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CropDin
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.ViewAgenda
import androidx.compose.ui.graphics.vector.ImageVector
import com.mk.sheets.compose.R

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    @StringRes val topBarTitle: Int,
) {

    object ShowcaseDialogSamples : Screen(
        route = "showcase_dialogs",
        icon = Icons.Rounded.GridView,
        topBarTitle = R.string.showcase_dialogs
    )

    object ShowcasePopup : Screen(
        route = "showcase_popup",
        icon = Icons.Rounded.CropDin,
        topBarTitle = R.string.screen_showcase_popup
    )

    object ShowcaseBottomSheet : Screen(
        route = "showcase_bottom_sheet",
        icon = Icons.Rounded.ViewAgenda,
        topBarTitle = R.string.screen_showcase_bottom_sheet
    )

}