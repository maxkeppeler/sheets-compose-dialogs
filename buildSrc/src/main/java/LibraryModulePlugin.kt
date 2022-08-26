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

            implementation(Dependency.KOTLIN_STD)
            implementation(Dependency.AndroidX.CORE_KTX)

            // Compose libs
            implementation(Dependency.Compose.UI)
            implementation(Dependency.Compose.UI_TOOLING)
            implementation(Dependency.Compose.ANIMATION)
            implementation(Dependency.Compose.RUNTIME)
            implementation(Dependency.Compose.MATERIAL)
            implementation(Dependency.Compose.MATERIAL_3)
            implementation(Dependency.Compose.ICONS_EXTENDED)

            // Test libs
            testImplementation(Dependency.Test.JUNIT)
            androidTestImplementation(Dependency.AndroidX.Test.JUNIT)
            androidTestImplementation(Dependency.AndroidX.Test.ESPRESSO_CORE)
            androidTestImplementation(Dependency.Compose.Test.JUNIT)
            debugImplementation(Dependency.Compose.Test.MANIFEST)
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