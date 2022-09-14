plugins {
    id("com.android.application") version ("7.2.2") apply false
    id("com.android.library") version ("7.2.2") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.0") apply false
    id("com.diffplug.spotless") version ("6.10.0")
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    plugins.apply("com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
//            ktlint(Versions.KT_LINT)
            licenseHeaderFile(rootProject.file("copyright.kt"))
        }
        kotlinGradle {
            target("*.gradle.kts", "gradle/*.gradle.kts", "buildSrc/*.gradle.kts")
            licenseHeaderFile(rootProject.file("copyright.kt"), "import|tasks|apply|plugins|rootProject")
        }
    }
}