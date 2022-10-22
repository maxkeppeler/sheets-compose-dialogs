@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core.functional.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.views.HeaderComponent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeaderComponentTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun headerDisplaysCustomHeader() {
        var customHeaderCalled = false
        rule.setContent {
            HeaderComponent(
                header = Header.Custom { customHeaderCalled = true },
                contentHorizontalPadding = PaddingValues()
            )
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT).assertDoesNotExist()
        assert(customHeaderCalled)
    }

    @Test
    fun headerDisplaysDefaultHeader() {
        rule.setContent {
            HeaderComponent(
                header = Header.Default("Title", IconSource(Icons.Rounded.Face)),
                contentHorizontalPadding = PaddingValues()
            )
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT_TEXT).assertExists()
    }

    @Test
    fun headerDisplaysDefaultHeaderTextNoIcon() {
        val text = "title"
        rule.setContent {
            HeaderComponent(
                header = Header.Default(text, null),
                contentHorizontalPadding = PaddingValues()
            )
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT_TEXT).apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT_ICON).apply {
            assertDoesNotExist()
        }
    }

    @Test
    fun headerDisplaysDefaultHeaderTextWithIcon() {
        val text = "title"
        rule.setContent {
            HeaderComponent(
                header = Header.Default(text, IconSource(Icons.Rounded.Face)),
                contentHorizontalPadding = PaddingValues()
            )
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT_TEXT).apply {
            assertIsDisplayed()
            assertTextEquals(text)
        }
        rule.onNodeWithTag(TestTags.HEADER_DEFAULT_ICON).apply {
            assertExists()
            assertIsDisplayed()
        }
    }


}