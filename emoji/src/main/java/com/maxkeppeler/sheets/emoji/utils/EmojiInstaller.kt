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
package com.maxkeppeler.sheets.emoji.utils

import com.maxkeppeler.sheets.emoji.models.EmojiProvider
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.facebook.FacebookEmojiProvider
import com.vanniktech.emoji.google.GoogleEmojiProvider
import com.vanniktech.emoji.ios.IosEmojiProvider
import com.vanniktech.emoji.twitter.TwitterEmojiProvider

/**
 * Helper class that will install and destroy the selected emoji provider as this can not be done in the Application class.
 *
 */
internal object EmojiInstaller {

    private var initiated = false

    /**
     * Installs an emoji provider.
     * @param emojiProvider The emoji provider that will be installed.
     */
    fun installProvider(emojiProvider: EmojiProvider) {
        synchronized(EmojiInstaller::class.java) {
            if (!initiated) {
                when (emojiProvider) {
                    EmojiProvider.GOOGLE -> EmojiManager.install(GoogleEmojiProvider())
                    EmojiProvider.IOS -> EmojiManager.install(IosEmojiProvider())
                    EmojiProvider.FACEBOOK -> EmojiManager.install(FacebookEmojiProvider())
                    EmojiProvider.TWITTER -> EmojiManager.install(TwitterEmojiProvider())
                }
                initiated = true
            }
        }
    }

    /**
     * The emoji provider will be destroyed.
     */
    fun destroyProvider() {
        EmojiManager.destroy()
        initiated = false
    }
}