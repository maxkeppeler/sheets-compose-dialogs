package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.SelectionButton
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
    onCancel: () -> Unit,
    onPositiveValid: Boolean = true,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.scd_small_100))
            .padding(horizontal = dimensionResource(id = R.dimen.scd_normal_100)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        SelectionButtonComponent(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = dimensionResource(id = R.dimen.scd_normal_100)),
            button = selection.negativeButton,
            onClick = { onNegative(); onCancel() },
            defaultText = stringResource(id = R.string.cancel)
        )

        SelectionButtonComponent(
            modifier = Modifier
                .wrapContentWidth(),
            button = selection.positiveButton,
            onClick = { onPositive(); onCancel() },
            enabled = onPositiveValid,
            defaultText = stringResource(id = R.string.ok)
        )
    }
}

/**
 * A helper component to setup the right button.
 * @param modifier The modifier that is applied to the button.
 * @param button The data that is used to build this button.
 * @param onClick Listener that is invoked when the button is clicked.
 * @param enabled Controls the enabled state of this button. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param defaultText The text that is used by default in the button data does not contain a text.
 */
@Composable
private fun SelectionButtonComponent(
    modifier: Modifier,
    button: SelectionButton?,
    onClick: () -> Unit,
    enabled: Boolean = true,
    defaultText: String
) {
    val buttonContent: @Composable RowScope.() -> Unit = {
        button?.icon?.let { icon ->
            IconComponent(
                modifier = Modifier.size(dimensionResource(R.dimen.scd_size_100)),
                iconSource = icon,
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.scd_small_100)))
        }
        Text(text = button?.text ?: defaultText)
    }

    when {
        button == null || button.type == ButtonStyle.TEXT -> {
            TextButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        }
        button.type == ButtonStyle.FILLED -> {
            Button(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        }
        button.type == ButtonStyle.ELEVATED -> {
            ElevatedButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = buttonContent
            )
        }
    }

}