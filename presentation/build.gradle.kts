@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.buster.android.compose)
    alias(libs.plugins.buster.android.library)
    alias(libs.plugins.buster.kotlin.android)
    alias(libs.plugins.buster.android.hilt)
    alias(libs.plugins.buster.android.sharedLibrary)
    alias(libs.plugins.buster.android.room)
}

android {
    namespace = "com.joel.presentation"

}

dependencies {

    implementation(project(":presentation"))
    implementation(libs.androidx.paging)


}