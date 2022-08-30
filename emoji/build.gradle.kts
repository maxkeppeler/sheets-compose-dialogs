plugins {
    id("library-module")
}

android {
    defaultConfig {
        compileSdk = App.COMPILE_SDK
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        testInstrumentationRunner = App.TEST_INSTRUMENTATION_RUNNER
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
    api(project(Modules.CORE.path))
    implementation(Dependencies.Vanniktech.EMOJI_GOOGLE)
    implementation(Dependencies.Vanniktech.EMOJI_IOS)
    implementation(Dependencies.Vanniktech.EMOJI_TWITTER)
    implementation(Dependencies.Vanniktech.EMOJI_FACEBOOK)
}