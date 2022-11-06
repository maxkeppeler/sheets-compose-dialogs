import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.project

class LibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(Plugins.LIBRARY.id)
            apply(Plugins.KOTLIN.id)
            apply(Plugins.DOKKA.id)
            apply(Plugins.MAVEN_PUBLISH.id)
        }
    }

    private fun Project.applyDependencies() {

        dependencies.apply {

            // All modules require the core module

            if (name != Modules.CORE.moduleName) {
                apis(project(Modules.CORE.path))
            }


            // AndroidX & Kotlin libs

            implementations(Dependencies.Kotlin.KOTLIN_STD)
            implementations(Dependencies.AndroidX.CORE_KTX)


            // Compose libs

            implementations(
                Dependencies.Compose.UI,
                Dependencies.Compose.UI_TOOLING,
                Dependencies.Compose.ANIMATION,
                Dependencies.Compose.ANIMATION_GRAPHICS,
                Dependencies.Compose.RUNTIME,
                Dependencies.Compose.MATERIAL_3,
                Dependencies.Compose.ICONS_EXTENDED,
            )


            // Test libs

            androidTestImplementations(
                Dependencies.AndroidX.Test.JUNIT,
                Dependencies.AndroidX.Test.ESPRESSO_CORE,
                Dependencies.Compose.Test.JUNIT,
                project(":test")
            )

            debugImplementations(
                Dependencies.Compose.Test.TOOLING,
                Dependencies.Compose.Test.MANIFEST
            )
            testImplementations(Dependencies.Test.JUNIT)
        }
    }
}