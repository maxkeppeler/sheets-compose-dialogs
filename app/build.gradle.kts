/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
    id(Plugins.APPLICATION.id)
    id(Plugins.KOTLIN.id)
}

android {
    namespace = App.ID
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
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Modules

    Modules.values().forEach { module ->
        apis(project(module.path))
    }

    // Dependencies of sheets-compose-dialogs
//
//    val sheetsVersion = "0.0.1"
//
//    implementation("com.maxkeppeler.sheets-compose-dialogs:core:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:info:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:color:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:duration:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:date-time:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:option:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:list:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:input:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:state:$sheetsVersion")
//    implementation("com.maxkeppeler.sheets-compose-dialogs:emoji:$sheetsVersion")


    coreLibraryDesugaring(Dependencies.DESUGAR)

    // Kotlin libs

    implementations(Dependencies.Kotlin.KOTLIN_STD)


    // AndroidX libs

    implementations(
        Dependencies.AndroidX.CORE_KTX,
        Dependencies.AndroidX.LIFECYCLE_KTX,
        Dependencies.AndroidX.ACTIVITY_COMPOSE,
        Dependencies.AndroidX.NAVIGATION_COMPOSE,
    )


    // Compose libs

    implementations(
        platform(Dependencies.Compose.BOM),
        Dependencies.Compose.UI,
        Dependencies.Compose.UI_TOOLING,
        Dependencies.Compose.ANIMATION,
        Dependencies.Compose.RUNTIME,
        Dependencies.Compose.MATERIAL_2,
        Dependencies.Compose.MATERIAL_3,
        Dependencies.Compose.ICONS_EXTENDED,
    )

    // Test libs

    androidTestImplementations(
        Dependencies.AndroidX.Test.JUNIT,
        Dependencies.AndroidX.Test.ESPRESSO_CORE,
        Dependencies.Compose.Test.JUNIT,
    )
    debugImplementations(
        Dependencies.Compose.Test.TOOLING,
        Dependencies.Compose.Test.MANIFEST
    )
    testImplementation(Dependencies.Test.JUNIT)
}