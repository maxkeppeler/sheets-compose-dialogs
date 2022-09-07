package com.maxkeppeler.sheets.state.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * The general configuration for the state dialog.
 * @param state The state of dialog that will be shown.
 */
data class StateConfig(
    val state: State,
) : BaseConfigs()