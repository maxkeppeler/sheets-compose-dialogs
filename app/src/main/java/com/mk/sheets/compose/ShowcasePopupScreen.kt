@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.core.R
import com.mk.sheets.compose.samples.ShowcasePopupSample

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

        ShowcasePopupSample(
            visible = showPopup,
            onHide = { showPopup = false }
        )
    }
}

