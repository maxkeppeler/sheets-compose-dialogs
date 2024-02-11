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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.emoji.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.emoji.EmojiView
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryAppearance
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.test.utils.onNodeWithTags
import com.maxkeppeler.sheets.test.utils.onPositiveButton
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmojiViewTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun emojiViewUnicodeSelection() {
        val testEmoji = "😆"
        var selectedEmoji: String? = null
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Unicode { selectedEmoji = it },
                config = EmojiConfig()
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_SELECTION, testEmoji).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedEmoji.equals(testEmoji))
    }

    @Test
    fun emojiViewEmojiSelection() {
        val testEmoji = "😆"
        var selectedEmoji: String? = null
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Emoji { selectedEmoji = it.unicode },
                config = EmojiConfig()
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_SELECTION, testEmoji).performClick()
        rule.onPositiveButton().performClick()
        assert(selectedEmoji.equals(testEmoji))
    }

    @Test
    fun emojiViewDisplaysTextCategories() {
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Unicode { },
                config = EmojiConfig(categoryAppearance = EmojiCategoryAppearance.TEXT)
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY, EmojiCategoryAppearance.TEXT)
            .apply {
                assertExists()
                assertIsDisplayed()
            }
    }

    @Test
    fun emojiViewDisplaysSymbolCategories() {
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Unicode { },
                config = EmojiConfig(categoryAppearance = EmojiCategoryAppearance.SYMBOL)
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY, EmojiCategoryAppearance.SYMBOL)
            .apply {
                assertExists()
                assertIsDisplayed()
            }
    }

    @Test
    fun emojiViewSwitchTextTabs() {
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Unicode { },
                config = EmojiConfig(categoryAppearance = EmojiCategoryAppearance.TEXT)
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 0).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 1).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 2).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 3).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY, EmojiCategoryAppearance.TEXT).performTouchInput { swipeRight() }
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 0).performClick()
    }

    @Test
    fun emojiViewSwitchSymbolTabs() {
        rule.setContentAndWaitForIdle {
            EmojiView(
                useCaseState = UseCaseState(visible = true),
                selection = EmojiSelection.Unicode { },
                config = EmojiConfig(categoryAppearance = EmojiCategoryAppearance.SYMBOL)
            )
        }
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 0).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 1).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 2).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 3).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 4).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 5).performClick()
        rule.onNodeWithTags(TestTags.EMOJI_CATEGORY_TAB, 0).performClick()
    }
}