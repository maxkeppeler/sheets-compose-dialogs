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