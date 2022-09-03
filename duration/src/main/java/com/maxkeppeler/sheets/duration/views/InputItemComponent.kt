package com.maxkeppeler.sheets.duration.views

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.duration.utils.Constants

@Composable
internal fun InputItemComponent(
    option: String,
    onBackspaceAction: () -> Unit,
    onEmptyAction: () -> Unit,
    onEnterValue: (String) -> Unit
) {
    val haptic = LocalHapticFeedback.current

    var cornerRadius by remember { mutableStateOf(Constants.DEFAULT_CORNER_RADIUS) }
    val animatedCornerRadius = animateIntAsState(cornerRadius, tween(durationMillis = 300))
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    if (isPressed) {
        if (cornerRadius == Constants.DEFAULT_CORNER_RADIUS) {
            cornerRadius = Constants.PRESSED_CORNER_RADIUS
        }
    } else {
        cornerRadius = Constants.DEFAULT_CORNER_RADIUS
    }

    val isActionBackspace = option == Constants.ACTION_BACKSPACE
    val isActionClear = option == Constants.ACTION_CLEAR

    Row(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(animatedCornerRadius.value))
            .background(
                if (isActionBackspace || isActionClear) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = {
                    if (isActionBackspace) onBackspaceAction()
                    else if (isActionClear) onEmptyAction()
                    else onEnterValue(option)
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                },
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isActionBackspace || isActionClear) {
            Icon(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.size_175)),
                imageVector = if (isActionBackspace) Icons.Outlined.Backspace else Icons.Outlined.Clear,
                contentDescription = if (isActionBackspace) "Delete last value" else "Clear values",
                tint = MaterialTheme.colorScheme.secondary
            )
        } else {
            Text(
                text = option,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}