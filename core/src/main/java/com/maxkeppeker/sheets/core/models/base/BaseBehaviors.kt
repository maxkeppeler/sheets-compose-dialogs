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
package com.maxkeppeker.sheets.core.models.base

import com.maxkeppeker.sheets.core.utils.BaseConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Base behaviors for the use-case views.
 */
object BaseBehaviors {

    /**
     * A behavior construct for views to handle the automatic selection process when the button view is disabled.
     *
     * @param coroutine The coroutine scope that is used to execute the behavior on.
     * @param selection The base selection that is used to identify if the behavior should be enabled or not.
     * @param condition A custom additional condition when the button view is not enabled.
     * @param onDisableInput Listener that is invoked when the input is disabled as it should be blocked based on the behavior.
     * @param onSelection Listener that is invoked the automatic selection is executed.
     * @param onFinished Listener that is invoked when the behavior has ended.
     */
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