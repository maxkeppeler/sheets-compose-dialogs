/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId = App.ID
        compileSdk = App.COMPILE_SDK
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME
        testInstrumentationRunner = App.TEST_INSTRUMENTATION_RUNNER
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
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    Modules.values().forEach { module ->
        api(project(module.path))
    }

    implementation(Dependencies.KOTLIN_STD)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)

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
    debugImplementation(Dependencies.Compose.Test.TOOLING)
    debugImplementation(Dependencies.Compose.Test.MANIFEST)
}