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