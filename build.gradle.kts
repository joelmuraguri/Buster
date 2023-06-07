// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}