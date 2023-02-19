package com.maxkeppeker.sheets.core.models.base

/**
 * A class for time-based debouncing.
 *
 * @param delay The delay time in milliseconds for debouncing.
 */
class Debouncer(private val delay: Long) {

    private var lastTime = 0L

    private val currentTime: Long
        get() = System.currentTimeMillis()

    /**
     * Debounces the given action by delaying its execution for the specified delay time.
     * If the action is called before the delay time has passed since the last call, the action is not executed.
     *
     * @param action The action to be executed after the delay has passed.
     */
    fun debounce(action: () -> Unit) {
        if (currentTime - lastTime < delay) return
        lastTime = currentTime
        action.invoke()
    }
}