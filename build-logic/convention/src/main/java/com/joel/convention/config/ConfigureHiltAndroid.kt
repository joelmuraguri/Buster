package com.joel.convention.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureHilt(
    extension : CommonExtension<*,*,*,*,*>
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")



        dependencies {
            "implementation"(libs.findLibrary("hilt-android").get())
            "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())
            "kapt"(libs.findLibrary("hilt-compiler").get())
            "kaptAndroidTest"(libs.findLibrary("hilt-compiler").get())
        }
    }

}