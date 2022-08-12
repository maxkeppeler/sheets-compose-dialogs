@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.ContentPaste
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.color.utils.copyColorIntoClipboard
import com.maxkeppeler.sheets.color.utils.getFormattedColor
import com.maxkeppeler.sheets.color.utils.pasteColorFromClipboard
import kotlinx.coroutines.delay
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun ColorCustomInfoComponent(
    color: Int,
    onColorChange: (Int) -> Unit,
) {
    val context = LocalContext.current
    val colorPasteError = rememberSaveable { mutableStateOf<String?>(null) }
    val onCopyCustomColor = {
        copyColorIntoClipboard(
            ctx = context,
            label = "Color",
            value = getFormattedColor(color)
        )
    }
    val onPasteCustomColor = {
        pasteColorFromClipboard(
            ctx = context,
            onPastedColor = { onColorChange(it) },
            onPastedColorFailure = { colorPasteError.value = it },
        )
    }
    LaunchedEffect(colorPasteError.value) {
        delay(3000)
        colorPasteError.value = null
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ColorTemplateItemComponent(
            modifier = Modifier.size(56.dp),
            color = color,
            selected = false,
            onColorClick = onColorChange
        )

        ElevatedCard(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(start = 0.dp)) {
                    if (colorPasteError.value != null) {
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            text = colorPasteError.value!!,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.sheets_argb),
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier.padding(top = dimensionResource(id = RC.dimen.small_25)),
                            text = getFormattedColor(color),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1
                        )
                    }
                }
                if (colorPasteError == null) {
                    Spacer(modifier = Modifier.weight(1f))
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier,
                        onClick = onCopyCustomColor
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(dimensionResource(RC.dimen.size_150)),
                            imageVector = Icons.Rounded.ContentCopy,
                            contentDescription = "Copy color",
                        )
                    }
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier,
                        onClick = onPasteCustomColor
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(dimensionResource(RC.dimen.size_150)),
                            imageVector = Icons.Rounded.ContentPaste,
                            contentDescription = "Paste color",
                        )
                    }
                }
            }
        }
    }
}
