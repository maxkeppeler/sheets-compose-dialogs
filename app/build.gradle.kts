plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId = AppConfig.id
        compileSdk = AppConfig.compileSdk
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    Module.values().forEach { module ->
        api(project(module.path))
    }

    implementation(Dependency.KOTLIN_STD)
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE_KTX)
    implementation(Dependency.AndroidX.ACTIVITY_COMPOSE)

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
    debugImplementation(Dependency.Compose.Test.TOOLING)
    debugImplementation(Dependency.Compose.Test.MANIFEST)
}