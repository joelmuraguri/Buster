package com.joel.convention.config

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


fun Project.configureRoom(
    extension: LibraryExtension
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


        dependencies {
            "implementation"(libs.findLibrary("room-runtime").get())
            "implementation"(libs.findLibrary("room-ktx").get())
            "implementation"(libs.findLibrary("room-paging").get())
            "annotationProcessor"(libs.findLibrary("room-compiler").get())
            "ksp"(libs.findLibrary("room-compiler").get())
        }
    }

}
