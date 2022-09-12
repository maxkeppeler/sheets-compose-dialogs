@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cabin
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton

@Composable
internal fun CoreSample1(closeSelection: () -> Unit) {

    CoreDialog(
        show = true,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                "nahhh",
                IconSource(Icons.Rounded.Notifications),
                ButtonStyle.FILLED
            ),
            positiveButton = SelectionButton(
                "yaah",
                IconSource(Icons.Rounded.Cabin),
                ButtonStyle.ELEVATED
            ),
        ),
        header = Header.Default(
            titleText = "Custom Dialog"
        ),
        onPositiveValid = true,
        body = {
            Text(text = "Test")
        },
        onClose = {
            closeSelection()
        }
    )
}