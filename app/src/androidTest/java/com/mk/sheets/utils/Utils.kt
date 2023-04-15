package com.mk.sheets

import android.graphics.Bitmap
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
    val actualPath = "/sheets${path?.let { "/$it" }}"
    val sheetsFolder = File(actualPath)

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