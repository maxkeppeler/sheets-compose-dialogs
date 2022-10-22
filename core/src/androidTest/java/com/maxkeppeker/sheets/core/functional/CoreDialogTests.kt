@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeler.sheets.test.utils.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoreDialogTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun coreDialogVisible() {
        rule.setContentAndWaitForIdle {
            CoreDialog(
                state = SheetState(visible = true),
                selection = CoreSelection(),
                body = { },
            )
        }
        rule.onDialog().assertExists()
        rule.onDialog().assertIsDisplayed()
    }

    @Test
    fun coreDialogNotVisible() {
        rule.setContentAndWaitForIdle {
            CoreDialog(
                state = SheetState(visible = false),
                selection = CoreSelection(),
                body = { },
            )
        }
        rule.onDialog().assertDoesNotExist()
    }

    @Test
    fun coreDialogInvokesPositiveButton() {
        var positiveCalled = false
        rule.setContent {
            CoreDialog(
                state = SheetState(visible = true),
                selection = CoreSelection(
                    onPositiveClick = { positiveCalled = true },
                ),
                body = { },
            )
        }
        rule.onPositiveButton().performClick()
        rule.onDialog().assertDoesNotExist()
        assert(positiveCalled)
    }

    @Test
    fun coreDialogInvokesNegativeButton() {
        var negativeCalled = false
        rule.setContent {
            CoreDialog(
                state = SheetState(visible = true),
                selection = CoreSelection(
                    onNegativeClick = { negativeCalled = true },
                ),
                body = { },
            )
        }
        rule.onNegativeButton().performClick()
        rule.onDialog().assertDoesNotExist()
        assert(negativeCalled)
    }

    @Test
    fun coreDialogInvokesExtraButton() {
        var extraCalled = false
        rule.setContent {
            CoreDialog(
                state = SheetState(visible = true),
                selection = CoreSelection(
                    extraButton = SelectionButton("test"),
                    onExtraButtonClick = { extraCalled = true },
                ),
                body = { },
            )
        }
        rule.onExtraButton().performClick()
        rule.onDialog().assertExists()
        assert(extraCalled)
    }

    @Test
    fun coreDialogDisplaysBody() {
        var bodyCalled = false
        rule.setContent {
            CoreDialog(
                selection = CoreSelection(),
                state = SheetState(visible = true),
                body = { bodyCalled = true },
            )
        }
        assert(bodyCalled)
    }
}