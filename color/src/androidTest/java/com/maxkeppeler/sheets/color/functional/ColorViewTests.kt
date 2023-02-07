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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.color.ColorView
import com.maxkeppeler.sheets.color.models.*
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun colorViewTemplateColorSelectionSuccess() {
        val testColors = listOf(
            Color.Red.toArgb(),
            Color.Gray.toArgb(),
            Color.Green.toArgb()
        )
        var selectedColor: Int? = null
        rule.setContentAndWaitForIdle {
            ColorView(
                sheetState = SheetState(visible = true),
                selection = ColorSelection { selectedColor = it },
                config = ColorConfig(
                    templateColors = MultipleColors.ColorsInt(testColors),
                    defaultDisplayMode = ColorSelectionMode.TEMPLATE
                )
            )
        }
        rule.onNodeWithTags(
            TestTags.COLOR_TEMPLATE_SELECTION,
            testColors[1]
        ).performClick()
        rule.onPositiveButton().performClick()
        assert(testColors[1] == selectedColor)
    }

    @Test
    fun colorViewSelectionInvalid() {
        rule.setContentAndWaitForIdle {
            ColorView(
                sheetState = SheetState(visible = true),
                selection = ColorSelection { },
                config = ColorConfig(defaultDisplayMode = ColorSelectionMode.TEMPLATE)
            )
        }
        rule.onPositiveButton().assertIsNotEnabled()
    }

    @Test
    fun colorViewCustomColorSelectionSuccess() {
        val testColor = Color.Red.toArgb()
        var selectedColor: Int? = null
        rule.setContentAndWaitForIdle {
            ColorView(
                sheetState = SheetState(visible = true),
                selection = ColorSelection(selectedColor = SingleColor(testColor)) {
                    selectedColor = it
                },
                config = ColorConfig(defaultDisplayMode = ColorSelectionMode.CUSTOM)
            )
        }
        rule
            .onNodeWithTags(TestTags.COLOR_CUSTOM_VALUE_SLIDER, 0)
            .performSemanticsAction(SemanticsActions.SetProgress) { it(30f) }

        rule
            .onNodeWithTags(TestTags.COLOR_CUSTOM_VALUE_SLIDER, 1)
            .performSemanticsAction(SemanticsActions.SetProgress) { it(10f) }

        rule
            .onNodeWithTags(TestTags.COLOR_CUSTOM_VALUE_SLIDER, 2)
            .performSemanticsAction(SemanticsActions.SetProgress) { it(240f) }

        rule
            .onNodeWithTags(TestTags.COLOR_CUSTOM_VALUE_SLIDER, 3)
            .performSemanticsAction(SemanticsActions.SetProgress) { it(42f) }

        rule.onPositiveButton().performClick()
        assert(selectedColor?.alpha == 30)
        assert(selectedColor?.red == 10)
        assert(selectedColor?.green == 240)
        assert(selectedColor?.blue == 42)
    }

    @Test
    fun colorViewCustomColorAlwaysValid() {
        rule.setContentAndWaitForIdle {
            ColorView(
                sheetState = SheetState(visible = true),
                selection = ColorSelection { },
                config = ColorConfig(defaultDisplayMode = ColorSelectionMode.CUSTOM)
            )
        }
        rule.onPositiveButton().assertIsEnabled()
    }


}