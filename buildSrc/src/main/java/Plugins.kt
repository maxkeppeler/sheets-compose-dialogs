import models.Plugin

object Plugins {

    val APPLICATION = Plugin("com.android.application", "7.2.2")
    val LIBRARY = Plugin("com.android.library", "7.2.2")
    val KOTLIN = Plugin("org.jetbrains.kotlin.android", Versions.KOTLIN)
    val SPOTLESS = Plugin("com.diffplug.spotless", "6.10.0")
    val MAVEN_PUBLISH = Plugin("com.vanniktech.maven.publish")
    val DOKKA = Plugin("org.jetbrains.dokka", Versions.DOKKA)

    val CUSTOM_LIBRARY_MODULE = Plugin("library-module")
}