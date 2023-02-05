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

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration


/**
 * Joins multiple test tags together into a sequence.
 * @param testTags The test tags you apply to the element.
 */
fun testSequenceTagOf(vararg testTags: Any): String = testTags.joinToString()

private const val TABLET_THRESHOLD = 800

@Composable
fun shouldUseLandscape(): Boolean =
    LocalConfiguration.current.screenHeightDp < TABLET_THRESHOLD