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
package com.mk.sheets

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import java.io.File
import java.io.FileOutputStream

/**
 * Saves a screenshot of the given node to the external storage.
 * @param node The node to take the screenshot from.
 * @param fileName The name of the file to save the screenshot to.
 */
internal fun saveScreenshotToFile(
    node: SemanticsNodeInteraction,
    path: String? = null,
    fileName: String
) {
    val bitmap: Bitmap = node.captureToImage().asAndroidBitmap()

    // Get the Downloads folder
    val downloadsFolder =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val actualPath = "sheets${path?.let { "/$it" }}"
    val sheetsFolder = File(downloadsFolder, actualPath)

    // Create the sheets directory if it doesn't exist
    if (!sheetsFolder.exists()) {
        sheetsFolder.mkdirs()
    }

    val file = File(sheetsFolder, "$fileName.png")
    Log.d("Screenshot", "Saving screenshot to ${file.absolutePath}")

    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
}