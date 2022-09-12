package com.maxkeppeker.sheets.core.models.base

import com.maxkeppeker.sheets.core.utils.BaseConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Base configs for dialog-specific configs.
 */

object BaseBehaviors {

    fun autoFinish(
        coroutine: CoroutineScope,
        selection: BaseSelection,
        condition: Boolean = true,
        onDisableInput: () -> Unit = {},
        onSelection: () -> Unit,
        onFinished: () -> Unit,
    ) {
        if (!selection.withButtonView && condition) {
            coroutine.launch {
                onDisableInput()
                delay(BaseConstants.SUCCESS_DISMISS_DELAY)
                onSelection()
                onFinished()
            }
        }
    }

}