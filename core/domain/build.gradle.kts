import org.gradle.kotlin.dsl.dependencies

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.buster.android.library)
    alias(libs.plugins.buster.kotlin.android)
}

android {
    namespace = "com.joel.domain"

}

dependencies {
    implementation(project(":core:data"))
    implementation(libs.androidx.paging)

}