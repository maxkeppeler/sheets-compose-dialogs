package com.maxkeppeler.sheets.test.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.maxkeppeker.sheets.core.utils.TestTags

fun ComposeContentTestRule.setContentAndWaitForIdle(content: @Composable () -> Unit) {
    setContent(content)
    waitForIdle()
}

fun ComposeTestRule.onDialog() = onNodeWithTag(TestTags.DIALOG_BASE_CONTENT)

fun ComposeTestRule.onView() = onNodeWithTag(TestTags.FRAME_BASE_CONTENT)

fun ComposeTestRule.onExtraButton() = onNodeWithTag(TestTags.BUTTON_EXTRA)

fun ComposeTestRule.onPositiveButton() = onNodeWithTag(TestTags.BUTTON_POSITIVE)

fun ComposeTestRule.onNegativeButton() = onNodeWithTag(TestTags.BUTTON_NEGATIVE)

fun ComposeTestRule.onNodeWithTags(vararg testTag: Any) = onNodeWithTag(testTag.joinToString(), true)

