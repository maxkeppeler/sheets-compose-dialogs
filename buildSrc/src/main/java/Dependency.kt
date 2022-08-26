//version constants for the Kotlin DSL dependencies
object Versions {
    //app level
    const val gradle = "4.0.1"
    const val kotlin = "1.7.0"

    const val compose = "1.2.0"

    //libs
    const val coreKtx = "1.2.0"
    const val appcompat = "1.3.0-alpha01"
    const val constraintLayout = "2.0.0-beta8"

    //test
    const val junit = "4.13.2"
    const val extJunit = "1.1.1"
    const val espresso = "3.2.0"
    const val testRunner = "1.1.1"
    const val espressoCore = "3.4.0"
}

enum class Module(val path: String) {
    CORE(":core"),
    INFO(":info"),
    CALENDAR(":calendar"),
    CLOCK_TIME(":clock_time"),
    TIME(":time"),
    COLOR(":color"),
    STATE(":state"),
    OPTION(":option")
}

object Dependency {

    object AndroidX {

        const val CORE_KTX ="androidx.core:core-ktx:1.8.0"
        const val LIFECYCLE_KTX ="androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val ACTIVITY_COMPOSE ="androidx.activity:activity-compose:1.5.1"

        object Test {
            const val JUNIT = "androidx.test.ext:junit:1.1.3"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"


        }
    }
    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val SNAPPER = "dev.chrisbanes.snapper:snapper:0.2.2"
    const val DESUGAR = "com.android.tools:desugar_jdk_libs:1.1.5"

    object Compose {
        const val UI = "androidx.compose.ui:ui:${Versions.compose}"
        const val RUNTIME = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.compose}"
        const val MATERIAL_3 = "androidx.compose.material3:material3:1.0.0-alpha15"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val ANIMATION = "androidx.compose.animation:animation:${Versions.compose}"


        object Test {
            const val JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
            const val TOOLING = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val MANIFEST ="androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        }
    }


    object Test {
        const val JUNIT = "junit:junit:${Versions.junit}"
        const val TEST_RUNNER = "androidx.test:runner:${Versions.testRunner}"
       }
}