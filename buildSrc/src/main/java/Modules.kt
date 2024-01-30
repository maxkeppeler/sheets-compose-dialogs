@file:Suppress("PropertyName")

enum class Modules(val moduleName: String) {

    CORE("core"),
    RATING("rating"),
    INFO("info"),
    STATE("state"),
    COLOR("color"),
    CALENDAR("calendar"),
    DATE_TIME("date_time"),
    CLOCK("clock"),
    DURATION("duration"),
    OPTION("option"),
    LIST("list"),
    INPUT("input"),
    EMOJI("emoji");

    val path: String
        get() = ":$moduleName"

    val namespace: String
        get() = "com.maxkeppeler.sheets.$moduleName"
}