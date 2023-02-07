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
package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.core.R

/**
 * Header component of the dialog.
 * @param selection The selection configuration for the dialog.
 * @param onPositive Listener that is invoked when the positive button is clicked.
 * @param onNegative Listener that is invoked when the negative button is clicked.
 * @param onPositiveValid If the positive button is valid and therefore enabled.
 */
@ExperimentalMaterial3Api
@Composable
fun ButtonsComponent(
    selection: BaseSelection,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
    onClose: () -> Unit,
    onPositiveValid: Boolean = true,
) {

    Row(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.scd_small_100))
            .padding(horizontal = dimensionResource(id = R.dimen.scd_normal_100)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        selection.extraButton?.let { extraButton ->
            SelectionButtonComponent(
                modifier = Modifier
                    .wrapContentWidth(),
                button = extraButton,
                onClick = { selection.onExtraButtonClick?.invoke() },
                testTag = TestTags.BUTTON_EXTRA,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        selection.negativeButton?.let { negativeButton ->
            SelectionButtonComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.scd_normal_100)),
                button = negativeButton,
                onClick = { onNegative(); onClose() },
                testTag = TestTags.BUTTON_NEGATIVE,
            )
        }

        SelectionButtonComponent(
            modifier = Modifier
                .wrapContentWidth(),
            button = selection.positiveButton,
            onClick = { onPositive(); onClose() },
            enabled = onPositiveValid,
            testTag = TestTags.BUTTON_POSITIVE,
        )
    }
}

/**
 * A helper component to setup a button.
 * @param modifier The modifier that is applied to the button.
 * @param button The data that is used to build this button.
 * @param onClick Listener that is invoked when the button is clicked.
 * @param enabled Controls the enabled state of this button. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param testTag The text that is used for the test tag.
 */
@Composable
private fun SelectionButtonComponent(
    modifier: Modifier,
    button: SelectionButton,
    onClick: () -> Unit,
    enabled: Boolean = true,
    testTag: String
) {
    val buttonContent: @Composable RowScope.() -> Unit = {
        button.icon?.let { icon ->
            IconComponent(
                modifier = Modifier
                    .testTags(testTag, TestTags.BUTTON_ICON)
                    .size(dimensionResource(R.dimen.scd_size_100)),
                iconSource = icon,
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.scd_small_100)))
        }

        when {
            button.text != null -> Text(text = button.text)
            button.textRes != null -> Text(text = stringResource(button.textRes))
            button.annotatedString != null -> Text(text = button.annotatedString)
            else -> throw IllegalStateException("Please correct your setup. The text is missing for a button.")
        }
    }

    when (button.type) {
        ButtonStyle.TEXT ->
            TextButton(
                modifier = modifier.testTag(testTag),
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        ButtonStyle.FILLED ->
            Button(
                modifier = modifier.testTag(testTag),
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        ButtonStyle.ELEVATED ->
            ElevatedButton(
                modifier = modifier.testTag(testTag),
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        ButtonStyle.OUTLINED ->
            OutlinedButton(
                modifier = modifier.testTag(testTag),
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
    }

}