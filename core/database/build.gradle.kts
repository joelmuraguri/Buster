@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.buster.android.library)
    alias(libs.plugins.buster.kotlin.android)
    alias(libs.plugins.buster.android.room)
    alias(libs.plugins.buster.android.hilt)
    alias(libs.plugins.buster.android.sharedLibrary)
}

android {
    namespace = "com.joel.database"

}

dependencies {
    implementation(libs.androidx.paging)
}