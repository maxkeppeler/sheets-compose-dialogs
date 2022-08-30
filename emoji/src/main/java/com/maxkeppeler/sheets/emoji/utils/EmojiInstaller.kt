package com.maxkeppeler.sheets.emoji.utils

import com.maxkeppeler.sheets.emoji.models.EmojiProvider
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.facebook.FacebookEmojiProvider
import com.vanniktech.emoji.google.GoogleEmojiProvider
import com.vanniktech.emoji.ios.IosEmojiProvider
import com.vanniktech.emoji.twitter.TwitterEmojiProvider

internal object EmojiInstaller {

    private var initiated = false

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

    fun destroyProvider() {
        EmojiManager.destroy()
        initiated = false
    }
}