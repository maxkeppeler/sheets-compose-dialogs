@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.option.functional

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.option.OptionView
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OptionViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun optionViewListSingleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 5, 4, 2, 1, 0)
        var selectedIndex: Int? = null
        var selectedOption: Option? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Single(
                    options = testOptions,
                    onSelectOption = { index, option ->
                        selectedIndex = index
                        selectedOption = option
                    }
                ),
                config = OptionConfig(mode = DisplayMode.LIST)
            )
        }
        testIndices.forEach {
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, it).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndex == testIndices.last())
        assert(selectedOption?.position == testIndices.last())
        assert(selectedOption?.titleText == testOptions[testIndices.last()].titleText)
        assert(selectedOption?.selected == true)
    }

    @Test
    fun optionViewListMultipleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 2, 5, 1, 5, 4, 0)
        var selectedIndices: List<Int>? = null
        var selectedOptions: List<Option>? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Multiple(
                    options = testOptions,
                    onSelectOptions = { index, option ->
                        selectedIndices = index
                        selectedOptions = option
                    }
                ),
                config = OptionConfig(mode = DisplayMode.LIST)
            )
        }
        testIndices.forEach { index ->
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, index).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndices?.contains(3) == true)
        assert(selectedIndices?.contains(4) == true)
        assert(selectedOptions?.all { it.selected } == true)
    }

    @Test
    fun optionViewGridVerticalSingleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 5, 4, 2, 1, 0)
        var selectedIndex: Int? = null
        var selectedOption: Option? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Single(
                    options = testOptions,
                    onSelectOption = { index, option ->
                        selectedIndex = index
                        selectedOption = option
                    },
                ),
                config = OptionConfig(mode = DisplayMode.GRID_VERTICAL)
            )
        }
        testIndices.forEach {
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, it).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndex == testIndices.last())
        assert(selectedOption?.position == testIndices.last())
        assert(selectedOption?.titleText == testOptions[testIndices.last()].titleText)
        assert(selectedOption?.selected == true)
    }

    @Test
    fun optionViewGridVerticalMultipleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 2, 5, 1, 5, 4, 0)
        var selectedIndices: List<Int>? = null
        var selectedOptions: List<Option>? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Multiple(
                    options = testOptions,
                    onSelectOptions = { index, option ->
                        selectedIndices = index
                        selectedOptions = option
                    }
                ),
                config = OptionConfig(mode = DisplayMode.GRID_VERTICAL)
            )
        }
        testIndices.forEach { index ->
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, index).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndices?.contains(3) == true)
        assert(selectedIndices?.contains(4) == true)
        assert(selectedOptions?.all { it.selected } == true)
    }

    @Test
    fun optionViewGridHorizontalSingleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 5, 4, 2, 1, 0)
        var selectedIndex: Int? = null
        var selectedOption: Option? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Single(
                    options = testOptions,
                    onSelectOption = { index, option ->
                        selectedIndex = index
                        selectedOption = option
                    },
                ),
                config = OptionConfig(mode = DisplayMode.GRID_HORIZONTAL)
            )
        }
        testIndices.forEach {
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, it).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndex == testIndices.last())
        assert(selectedOption?.position == testIndices.last())
        assert(selectedOption?.titleText == testOptions[testIndices.last()].titleText)
        assert(selectedOption?.selected == true)
    }

    @Test
    fun optionViewGridHorizontalMultipleSelection() {
        val testOptions = listOf(
            Option(titleText = "Test1"),
            Option(titleText = "Test2"),
            Option(titleText = "Test3"),
            Option(titleText = "Test4"),
            Option(titleText = "Test5"),
            Option(titleText = "Test6"),
        )
        val testIndices = listOf(0, 1, 2, 3, 2, 5, 1, 5, 4, 0)
        var selectedIndices: List<Int>? = null
        var selectedOptions: List<Option>? = null
        rule.setContentAndWaitForIdle {
            OptionView(
                sheetState = SheetState(visible = true),
                selection = OptionSelection.Multiple(
                    options = testOptions,
                    onSelectOptions = { index, option ->
                        selectedIndices = index
                        selectedOptions = option
                    }
                ),
                config = OptionConfig(mode = DisplayMode.GRID_HORIZONTAL)
            )
        }
        testIndices.forEach { index ->
            rule.onNodeWithTags(TestTags.OPTION_VIEW_SELECTION, index).performClick()
        }
        rule.onPositiveButton().performClick()

        assert(selectedIndices?.contains(3) == true)
        assert(selectedIndices?.contains(4) == true)
        assert(selectedOptions?.all { it.selected } == true)
    }
}