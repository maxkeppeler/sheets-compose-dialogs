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
package com.maxkeppeler.sheets.clock.utils

/**
 * Clock dialog specific constants.
 */
internal object Constants {

    const val ACTION_NEXT = "action_next"
    const val ACTION_PREV = "action_prev"

    // Keyboard constants

    const val KEYBOARD_COLUMNS = 3

    const val KEYBOARD_ANIM_CORNER_RADIUS = 300
    const val KEYBOARD_ITEM_CORNER_RADIUS_DEFAULT = 50
    const val KEYBOARD_ITEM_CORNER_RADIUS_PRESSED = 20

    const val KEYBOARD_ALPHA_ITEM_ENABLED = 1f
    const val KEYBOARD_ALPHA_ITEM_DISABLED = 0.3f

    const val KEYBOARD_ACTION_BACKGROUND_SURFACE_ALPHA = 0.3f
}