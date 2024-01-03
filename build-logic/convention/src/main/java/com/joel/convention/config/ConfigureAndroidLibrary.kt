package com.joel.convention.config

import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.utils.ProjectConfig
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configAndroidLibrary(
    extension : LibraryExtension
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        compileSdk = ProjectConfig.compileSdk

        defaultConfig {
            minSdk = ProjectConfig.minSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = ProjectConfig.sourceCompatibility
            targetCompatibility = ProjectConfig.targetCompatibility
        }

        kotlinOptions {
            jvmTarget = ProjectConfig.jvmTarget
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-core-ktx").get())
            "implementation"(libs.findLibrary("androidx-appcompact").get())
            "implementation"(libs.findLibrary("google-material").get())
            "testImplementation"(libs.findLibrary("junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-ext-junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-test-espresso").get())
        }
    }

}