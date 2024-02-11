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

/**
 * A class for time-based debouncing.
 *
 * @param delay The delay time in milliseconds for debouncing.
 */
class Debouncer(private val delay: Long) {

    private var lastTime = 0L

    private val currentTime: Long
        get() = System.currentTimeMillis()

    /**
     * Debounces the given action by delaying its execution for the specified delay time.
     * If the action is called before the delay time has passed since the last call, the action is not executed.
     *
     * @param action The action to be executed after the delay has passed.
     */
    fun debounce(action: () -> Unit) {
        if (currentTime - lastTime < delay) return
        lastTime = currentTime
        action.invoke()
    }
}