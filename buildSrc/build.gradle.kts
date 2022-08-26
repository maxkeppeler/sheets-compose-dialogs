plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("library-module") {
            id = "library-module"
            implementationClass = "LibraryModulePlugin"
        }
    }
}