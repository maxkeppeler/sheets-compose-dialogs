package com.mk.sheets.compose

enum class UseCaseCategory(val title: String) {
    CORE("⚒️  Core Dialog"),
    INFO("ℹ️  Info Dialog"),
    COLOR("\uD83C\uDFA8  Color Dialog"),
    CALENDAR("\uD83D\uDCC5  Calendar Dialog"),
    CLOCK("\uD83D\uDD67  Clock Dialog"),
    DATE_TIME("\uD83D\uDCC5\uD83D\uDD67  DateTime Dialog"),
    DURATION("⌛  Duration Dialog"),
    OPTION("\uD83E\uDEA7  Option Dialog"),
    LIST("✅️  List Dialog"),
    EMOJI("\uD83D\uDE1C  Emoji Dialog"),
    STATE("\uD83D\uDD03  State Dialog")
}