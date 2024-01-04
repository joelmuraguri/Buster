import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.buster.android.application)
    alias(libs.plugins.buster.kotlin.android)
    alias(libs.plugins.buster.android.compose)
    alias(libs.plugins.buster.android.sharedLibrary)
    alias(libs.plugins.buster.android.hilt)
    alias(libs.plugins.buster.android.ktor.client)

}

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties().apply {
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { stream ->
            load(stream)
        }
    }
}

android {
    namespace = "com.joel.buster"


//        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
//        buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
//        buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
//        buildConfigField("String", "ORIGINAL_IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "TMDB_API_KEY", "\"${localProperties.getProperty("TMDB_API_KEY")}\"")
        }
        debug {
            // configuration for debug builds
            buildConfigField("boolean", "DEBUG", "true")
        }
    }

}

dependencies {
    implementation(project(":core:network"))
}

