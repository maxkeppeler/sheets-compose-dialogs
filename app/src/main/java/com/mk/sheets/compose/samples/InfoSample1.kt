@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection

@Composable
internal fun InfoSample1(closeSelection: () -> Unit) {

    InfoDialog(
        show = true,
        header = Header.Default(
            titleText = "Do you want to work?",
            subtitleText = "It's essential"
        ),
        body = Body.Default(
            bodyText = "this is a very long text bla bla",
            postBody = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Test")
                }
            }
        ),
        selection = InfoSelection(onPositiveClick = {}, onNegativeClick = {}),
        onClose = { closeSelection() }
    )
}