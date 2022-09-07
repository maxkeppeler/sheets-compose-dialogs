package com.maxkeppeler.sheets.color.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import androidx.annotation.RestrictTo
import com.maxkeppeler.sheets.color.R

/** Save a text into the clipboard. */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun copyColorIntoClipboard(ctx: Context, label: String, value: String) {
    val clipboard = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, value)
    clipboard.setPrimaryClip(clip)
}

/** Receive the clipboard data. */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun pasteColorFromClipboard(
    ctx: Context,
    onPastedColor: (Int) -> Unit,
    onPastedColorFailure: (String) -> Unit,
) {
    val clipboard = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val item = clipboard.primaryClip?.getItemAt(0)
    val text = item?.text?.toString()
    text?.let {
        runCatching {
            // Color detected
            onPastedColor(Color.parseColor(it))
        }.getOrElse {
            // Clipboard information can not be parsed to color
            onPastedColorFailure(ctx.getString(R.string.scd_color_dialog_clipboard_paste_invalid_color_code))
        }
    } ?: run {
        // Clipboard was empty
        onPastedColorFailure(ctx.getString(R.string.scd_color_dialog_clipboard_paste_invalid_empty))
    }
}

/** Receive the clipboard data. */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun getFormattedColor(color: Int): String =
    String.format("#%08X", (0xFFFFFFFF and color.toLong()))
