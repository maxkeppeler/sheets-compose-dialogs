package com.maxkeppeler.sheets.state.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * Available calendar configurations.
 */
data class StateConfig(

    /**
     * Define the state of the dialog.
     */
    val state: State,

    ) : BaseConfigs()