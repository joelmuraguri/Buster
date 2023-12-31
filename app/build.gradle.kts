@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.buster.android.application)
    alias(libs.plugins.buster.kotlin.android)
    alias(libs.plugins.buster.android.compose)
    alias(libs.plugins.buster.android.sharedLibrary)
    alias(libs.plugins.buster.android.hilt)

}

android {
    namespace = "com.joel.buster"


//        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
//        buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
//        buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
//        buildConfigField("String", "ORIGINAL_IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")

}

