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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.list.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.list.ListView
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun listViewSingleSelection() {
        val testOptions = listOf(
            ListOption(titleText = "Test1"),
            ListOption(titleText = "Test2"),
            ListOption(titleText = "Test3"),
            ListOption(titleText = "Test4"),
            ListOption(titleText = "Test5"),
            ListOption(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 5, 4, 2, 1, 0)
        var selectedIndex: Int? = null
        var selectedOption: ListOption? = null
        rule.setContentAndWaitForIdle {
            ListView(
                sheetState = SheetState(visible = true),
                selection = ListSelection.Single(
                    options = testOptions,
                    onSelectOption = { index, option ->
                        selectedIndex = index
                        selectedOption = option
                    }
                ),
            )
        }
        testIndices.forEach {
            rule.onNodeWithTags(TestTags.LIST_VIEW_SELECTION, it).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndex == testIndices.last())
        assert(selectedOption?.position == testIndices.last())
        assert(selectedOption?.titleText == testOptions[testIndices.last()].titleText)
        assert(selectedOption?.selected == true)
    }

    @Test
    fun listViewMultipleSelection() {
        val testOptions = listOf(
            ListOption(titleText = "Test1"),
            ListOption(titleText = "Test2"),
            ListOption(titleText = "Test3"),
            ListOption(titleText = "Test4"),
            ListOption(titleText = "Test5"),
            ListOption(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 2, 5, 1, 5, 4, 0)
        var selectedIndices: List<Int>? = null
        var selectedOptions: List<ListOption>? = null
        rule.setContentAndWaitForIdle {
            ListView(
                sheetState = SheetState(visible = true),
                selection = ListSelection.Multiple(
                    options = testOptions,
                    onSelectOptions = { index, option ->
                        selectedIndices = index
                        selectedOptions = option
                    }
                ),
            )
        }
        testIndices.forEach { index ->
            rule.onNodeWithTags(TestTags.LIST_VIEW_SELECTION, index).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndices?.contains(3) == true)
        assert(selectedIndices?.contains(4) == true)
        assert(selectedOptions?.all { it.selected } == true)
    }
}