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
package com.maxkeppeler.sheets.emoji.utils

import com.maxkeppeler.sheets.emoji.models.EmojiConfig

/**
 * Emoji dialog specific constants.
 */
internal object Constants {

    fun getCategorySymbols(config: EmojiConfig) = listOf(
        config.icons.EmojiEmotions,
        config.icons.EmojiNature,
        config.icons.EmojiFoodBeverage,
        config.icons.EmojiTransportation,
        config.icons.EmojiEvents,
        config.icons.EmojiObjects,
        config.icons.EmojiSymbols,
        config.icons.EmojiFlags,
    )
}