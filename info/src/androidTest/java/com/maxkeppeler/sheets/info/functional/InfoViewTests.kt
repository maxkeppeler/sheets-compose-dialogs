@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.maxkeppeler.sheets.info.functional

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.info.InfoView
import com.maxkeppeler.sheets.info.models.InfoBody
import com.maxkeppeler.sheets.info.models.InfoSelection
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun infoViewDisplaysDefaultBody() {
        val text = "This is a default body."
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                sheetState = SheetState(visible = true),
                body = InfoBody.Default(text),
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT).assertExists()
    }

    @Test
    fun infoViewDisplaysDefaultBodyText() {
        val text = "This is a default body."
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                sheetState = SheetState(visible = true),
                body = InfoBody.Default(text),
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT_TEXT).apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
    }

    @Test
    fun infoViewDisplaysCustomBody() {
        var customBodyCalled = false
        rule.setContent {
            InfoView(
                selection = InfoSelection {},
                sheetState = SheetState(visible = true),
                body = InfoBody.Custom { customBodyCalled = true },
            )
        }
        rule.onNodeWithTag(TestTags.INFO_BODY_DEFAULT).assertDoesNotExist()
        assert(customBodyCalled)
    }

}