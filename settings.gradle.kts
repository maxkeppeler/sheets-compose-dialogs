pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "sheets-compose-dialogs"
include(
    ":app",
    ":test",
    // Include all modules
    ":core",
    ":number",
    ":info",
    ":rating",
    ":calendar",
    ":duration",
    ":clock",
    ":date_time",
    ":color",
    ":option",
    ":state",
    ":input",
    ":emoji",
    ":list",
)