pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "sheets-compose"
include(
    ":app",

    // Include all modules
    ":core",
    ":info",
    ":calendar",
    ":time",
    ":clock_time",
    ":color",
    ":option",
    ":state",
)