package com.maxkeppeler.sheets.clock_time.views

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
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.clock_time.R
import com.maxkeppeler.sheets.clock_time.utils.Constants
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun InputItemComponent(
    key: String,
    disabled: Boolean = false,
    onNextAction: () -> Unit,
    onPrevAction: () -> Unit,
    onEnterValue: (Int) -> Unit
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

    val isActionNext = key == Constants.ACTION_NEXT
    val isActionPrev = key == Constants.ACTION_PREV

    Row(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .alpha(if (disabled) 0.3f else 1f)
            .clip(RoundedCornerShape(animatedCornerRadius.value))
            .background(
                if (isActionNext || isActionPrev) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
            .clickable(
                enabled = !disabled,
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = {
                    if (isActionNext) onNextAction()
                    else if (isActionPrev) onPrevAction()
                    else onEnterValue(key.toInt())
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                },
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isActionNext || isActionPrev) {
            Icon(
                modifier = Modifier
                    .size(dimensionResource(RC.dimen.size_175)),
                imageVector = if (isActionNext) Icons.Outlined.ChevronRight else Icons.Outlined.ChevronLeft,
                contentDescription = stringResource(id = if (isActionNext) R.string.sheets_next_value else R.string.sheets_previous_value),
                tint = MaterialTheme.colorScheme.secondary
            )
        } else {
            Text(
                text = key,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}