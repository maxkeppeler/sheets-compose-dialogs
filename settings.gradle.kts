pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "sheets-compose-dialog"
include(
    ":app",
    // Include all modules
    ":core",
    ":info",
    ":calendar",
    ":time",
    ":clock_time",
    ":date_text",
    ":color",
    ":option",
    ":state",
    ":emoji",
    ":list",
)