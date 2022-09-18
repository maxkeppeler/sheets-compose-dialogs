@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mk.sheets.compose.samples.PopupSample

@Composable
internal fun ShowcasePopupScreen() {

    var showPopup by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ElevatedButton(
            onClick = { showPopup = true }
        ) {
            Text(text = "Show PopUp")
        }

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Showcase of a PopUp \nwith CalendarView",
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )

        PopupSample(
            visible = showPopup,
            onHide = { showPopup = false }
        )
    }
}

