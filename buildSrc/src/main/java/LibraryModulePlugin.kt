import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

class LibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            dependenciesConf()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("kotlin-android")
        }
    }

    private fun Project.dependenciesConf() {

        dependencies.apply {

            implementation(Dependencies.KOTLIN_STD)
            implementation(Dependencies.AndroidX.CORE_KTX)

            // Compose libs
            implementation(Dependencies.Compose.UI)
            implementation(Dependencies.Compose.UI_TOOLING)
            implementation(Dependencies.Compose.ANIMATION)
            implementation(Dependencies.Compose.RUNTIME)
            implementation(Dependencies.Compose.MATERIAL)
            implementation(Dependencies.Compose.MATERIAL_3)
            implementation(Dependencies.Compose.ICONS_EXTENDED)

            // Test libs
            testImplementation(Dependencies.Test.JUNIT)
            androidTestImplementation(Dependencies.AndroidX.Test.JUNIT)
            androidTestImplementation(Dependencies.AndroidX.Test.ESPRESSO_CORE)
            androidTestImplementation(Dependencies.Compose.Test.JUNIT)
            debugImplementation(Dependencies.Compose.Test.MANIFEST)
       }
    }

    //util functions for adding the different type dependencies from build.gradle file
    fun DependencyHandler.kapt(vararg list: String) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.implementation(vararg list: String) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.debugImplementation(vararg list: String) {
        list.forEach { dependency ->
            add("debugImplementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(vararg list: String) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(vararg list: String) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }
}