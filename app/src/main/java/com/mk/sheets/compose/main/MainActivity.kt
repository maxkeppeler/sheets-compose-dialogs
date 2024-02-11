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
@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.mk.sheets.compose.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mk.sheets.compose.R
import com.mk.sheets.compose.models.Screen
import com.mk.sheets.compose.ui.theme.ColorfulRandomTestTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ColorfulRandomTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrapBook(navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrapBook(
    navController: NavHostController,
) {
    val items =
        listOf(Screen.ShowcaseDialogSamples, Screen.ShowcasePopup, Screen.ShowcaseBottomSheet)
    val (screen, destination) = remember { mutableStateOf<Screen>(Screen.ShowcaseDialogSamples) }

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(screen, navController, items) },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        NavHostBuilder(
            navController = navController,
            innerPadding = innerPadding,
            destination = destination,
        )
    }
}

@Composable
private fun TopBar() {

    val context = LocalContext.current
    val gitHubUrl = "https://github.com/maxkeppeler/sheets-compose-dialogs"
    val paypalUrl = "https://www.paypal.com/paypalme/maximiliankeppeler"

    SmallTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name_full),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            IconButton(
                onClick = {
                    val intent = Intent.parseUri(paypalUrl, Intent.URI_INTENT_SCHEME)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.VolunteerActivism,
                    contentDescription = stringResource(R.string.donate_paypal),
                )
            }
            IconButton(
                onClick = {
                    val intent = Intent.parseUri(gitHubUrl, Intent.URI_INTENT_SCHEME)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = stringResource(R.string.github_repository),
                )
            }
        }
    )
}

@Composable
private fun BottomBar(
    screen: Screen,
    navController: NavHostController,
    items: List<Screen>
) {
    NavigationBar {
        items.forEach { mainScreen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = mainScreen.icon,
                        contentDescription = stringResource(id = mainScreen.topBarTitle)
                    )
                },
                label = { Text(stringResource(mainScreen.topBarTitle)) },
                selected = mainScreen == screen,
                onClick = {
                    navController.navigate(mainScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
