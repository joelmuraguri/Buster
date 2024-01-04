package com.joel.convention.config

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureKtorClient(
    extension : CommonExtension<*, *, *, *, *>
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


        dependencies {
            "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
            "implementation"(libs.findLibrary("ktor-client-android").get())
            "implementation"(libs.findLibrary("ktor-client-content-negotiation").get())
            "implementation"(libs.findLibrary("ktor-client-logging-jvm").get())
            "implementation"(libs.findLibrary("ktor-client-serialization").get())
            "implementation"(libs.findLibrary("ktor-serialization-kotlinx-json").get())
            "testImplementation"(libs.findLibrary("ktor-client-mock").get())
        }
    }

}