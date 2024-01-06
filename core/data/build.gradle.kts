@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.buster.android.library)
    alias(libs.plugins.buster.kotlin.android)
    alias(libs.plugins.buster.android.hilt)
    alias(libs.plugins.buster.android.sharedLibrary)
    alias(libs.plugins.buster.android.room)
}

android {
    namespace = "com.joel.data"
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(libs.androidx.paging)

}