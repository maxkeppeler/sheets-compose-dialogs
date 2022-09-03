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
    ":duration",
    ":clock",
    ":date_time",
    ":color",
    ":option",
    ":state",
    ":emoji",
    ":list",
)