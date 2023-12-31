package com.joel.convention.config

import com.android.build.api.dsl.CommonExtension
import com.joel.convention.utils.ProjectConfig
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureAndroidCompose(
    extension : CommonExtension<*, *, *, *, *>
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        defaultConfig{
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildFeatures{
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = ProjectConfig.kotlinCompilerExtensionVersion
        }

        dependencies {
            val composeBom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(platform(composeBom))
            "implementation"(libs.findLibrary("androidx-compose-ui").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-graphics").get())
            "implementation"(libs.findLibrary("androidx-compose-material3").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            "implementation"(libs.findLibrary("androidx-navigation").get())
            "implementation"(libs.findLibrary("androidx-core-ktx").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
            "testImplementation"(libs.findLibrary("junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-compose-ui-test-junit4").get())
            "androidTestImplementation"(libs.findLibrary("androidx-ext-junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-test-espresso").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }
}