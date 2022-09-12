package com.maxkeppeler.sheets.date_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseState
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

/**
 * Handles the date time state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class DateTimeState(
    val selection: DateTimeSelection,
    val config: DateTimeConfig,
    stateData: DateTimeStateData? = null
) : BaseState() {

    var dateSelection by mutableStateOf<LocalDate?>(stateData?.dateSelection)
    var timeSelection by mutableStateOf<LocalTime?>(stateData?.timeSelection)
    var valid by mutableStateOf(isValid())

    private fun checkValid() {
        valid = isValid()
    }

    private fun isValid(): Boolean = when (selection) {
        is DateTimeSelection.Date -> dateSelection != null
        is DateTimeSelection.Time -> timeSelection != null
        is DateTimeSelection.DateTime -> dateSelection != null && timeSelection != null
    }

    fun processSelection(date: LocalDate?) {
        dateSelection = date
        checkValid()
    }

    fun processSelection(time: LocalTime?) {
        timeSelection = time
        checkValid()
    }

    fun onFinish() {
        when (selection) {
            is DateTimeSelection.Date -> selection.onPositiveClick(dateSelection!!)
            is DateTimeSelection.Time -> selection.onPositiveClick(timeSelection!!)
            is DateTimeSelection.DateTime -> {
                val value = dateSelection!!.atTime(timeSelection!!)
                selection.onPositiveClick(value)
            }
        }
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: DateTimeSelection,
            config: DateTimeConfig
        ): Saver<DateTimeState, *> = Saver(
            save = { state -> DateTimeStateData(state.dateSelection, state.timeSelection) },
            restore = { data -> DateTimeState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class DateTimeStateData(
        val dateSelection: LocalDate?,
        val timeSelection: LocalTime?
    ) : Serializable
}