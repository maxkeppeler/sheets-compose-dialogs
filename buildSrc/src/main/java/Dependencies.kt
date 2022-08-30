object Dependencies {

    object AndroidX {

        const val CORE_KTX = "androidx.core:core-ktx:1.8.0"
        const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.5.1"

        object Test {
            const val JUNIT = "androidx.test.ext:junit:1.1.3"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
        }
    }

    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val SNAPPER = "dev.chrisbanes.snapper:snapper:0.2.2"
    const val DESUGAR = "com.android.tools:desugar_jdk_libs:1.1.5"

    object Compose {

        const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
        const val MATERIAL_3 = "androidx.compose.material3:material3:1.0.0-alpha15"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
        const val ANIMATION = "androidx.compose.animation:animation:${Versions.COMPOSE}"

        object Test {
            const val JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
            const val TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
            const val MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
        }
    }

    object Vanniktech {

        const val EMOJI_GOOGLE = "com.vanniktech:emoji-google:0.15.0"
        const val EMOJI_IOS = "com.vanniktech:emoji-ios:0.15.0"
        const val EMOJI_FACEBOOK = "com.vanniktech:emoji-facebook:0.15.0"
        const val EMOJI_TWITTER = "com.vanniktech:emoji-twitter:0.15.0"
    }

    object Test {

        const val JUNIT = "junit:junit:${Versions.JUNIT}"
    }
}