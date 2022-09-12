package com.maxkeppeker.sheets.core.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.Serializable

abstract class BaseState : Serializable {

    open var inputDisabled by mutableStateOf(false)

    fun disableInput() {
        inputDisabled = true
    }

}
