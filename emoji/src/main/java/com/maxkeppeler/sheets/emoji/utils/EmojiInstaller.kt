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