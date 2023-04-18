/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.mk.sheets.sample_images

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.test.utils.setContentAndWaitForIdle
import com.mk.sheets.compose.ShowcaseDialogSamples
import com.mk.sheets.compose.models.Sample
import com.mk.sheets.compose.ui.theme.ColorfulRandomTestTheme
import com.mk.sheets.saveScreenshotToFile
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Capture the content of the [ShowcaseDialogSamples] and save it to a file.
 */
@RunWith(Parameterized::class)
class CaptureDialogSamples(private val sample: Sample) {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun captureDialogSampleLight() {
        captureDialogSample(darkTheme = false)
        assert(true)
    }

    @Test
    fun captureDialogSampleDark() {
        captureDialogSample(darkTheme = true)
        assert(true)
    }

    /**
     * Set the content of the [ComposeContentTestRule] and capture the content.
     */
    private fun captureDialogSample(darkTheme: Boolean) {
        setContent(rule, darkTheme)
        captureContent(rule.onNodeWithTag(TestTags.DIALOG_BASE_CONTENT), darkTheme)
        assert(true)
    }

    /**
     * Set the content of the [ComposeContentTestRule] and wait until the content is idle.
     */
    private fun setContent(
        rule: ComposeContentTestRule,
        darkTheme: Boolean = false
    ) {
        rule.setContentAndWaitForIdle {
            ColorfulRandomTestTheme(
                darkTheme = darkTheme,
                content = { ShowcaseDialogSamples(sample) }
            )
        }
        rule.waitUntil(RECORDING_DELAY) { true }
    }

    /**
     * Capture the content of the [SemanticsNodeInteraction] and save it to a file.
     * The file will be saved in the following path:
     */
    private fun captureContent(node: SemanticsNodeInteraction, darkTheme: Boolean) {
        val type = if (darkTheme) TYPE_DARK else TYPE_LIGHT
        runBlocking {
            delay(RECORDING_DELAY)
            saveScreenshotToFile(
                node = node,
                path = "${sample.category.name.lowercase()}/$type",
                fileName = sample.name.lowercase()
            )
            saveScreenshotToFile(
                node = node,
                path = "_all",
                fileName = "${sample.category.name.lowercase()}_${sample.name.lowercase()}_$type"
            )
        }
    }

    companion object {

        /**
         * The delay between setting the content and capturing the content.
         */
        private const val RECORDING_DELAY = 2000L

        private const val TYPE_LIGHT = "light"
        private const val TYPE_DARK = "dark"

        /**
         * Create a [Parameterized.Parameters] for each [Sample].
         * This will create a test for each sample.
         * The [Parameterized.Parameters] will be passed to the constructor of this class.
         */
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = Sample.values().map { arrayOf(it) }
    }
}