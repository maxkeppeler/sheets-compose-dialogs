@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.input.functional

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.input.InputView
import com.maxkeppeler.sheets.input.models.InputDivider
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputViewInputDividerTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun inputViewDisplaysInputDivider() {
        val testInputs = listOf(InputDivider())
        rule.setContent {
            InputView(
                sheetState = SheetState(true),
                selection = InputSelection(testInputs),
            )
        }
        rule.onNodeWithTags(TestTags.INPUT_ITEM_DIVIDER, 0).assertExists()
    }
}