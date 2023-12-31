package com.joel.convention.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureSharedLibrary(
    extension : CommonExtension<*, *, *, *, *>
){

    extension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


        dependencies {
            "implementation"(libs.findLibrary("timber").get())
            "implementation"(libs.findLibrary("kotlin-coroutines").get())
            "testImplementation"(libs.findLibrary("kotlin-coroutines-test").get())
        }
    }

}