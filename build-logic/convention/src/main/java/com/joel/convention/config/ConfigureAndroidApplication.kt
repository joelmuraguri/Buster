package com.joel.convention.config

import com.android.build.api.dsl.ApplicationExtension
import com.joel.convention.utils.ProjectConfig
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureAndroidApplication(
    extension: ApplicationExtension
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        compileSdk = ProjectConfig.compileSdk


        defaultConfig {
            applicationId = "com.joel.buster"

            targetSdk = ProjectConfig.targetSdk
            minSdk = ProjectConfig.minSdk
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            buildConfig = true
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

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-activity-compose").get())
        }

    }
}