@file:OptIn(ExperimentalMaterialApi::class)

package com.mk.sheets.compose.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mk.sheets.compose.ShowcasePopupScreen
import com.mk.sheets.compose.ShowcaseDialogSamplesScreen
import com.mk.sheets.compose.ShowcaseBottomSheetScreen
import com.mk.sheets.compose.models.Screen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavHostBuilder(
    navController: NavHostController,
    innerPadding: PaddingValues,
    destination: (Screen) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.ShowcaseDialogSamples.route,
        modifier = Modifier.padding(innerPadding),
    ) {

        composable(Screen.ShowcaseDialogSamples.route) {
            destination(Screen.ShowcaseDialogSamples)
            ShowcaseDialogSamplesScreen()
        }

        composable(Screen.ShowcaseBottomSheet.route) {
            destination(Screen.ShowcaseBottomSheet)
            ShowcaseBottomSheetScreen()
        }

        composable(Screen.ShowcasePopup.route) {
            destination(Screen.ShowcasePopup)
            ShowcasePopupScreen()
        }
    }
}