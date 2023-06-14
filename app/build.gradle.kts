import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.org.apache.http.conn.util.PublicSuffixMatcherLoader.load

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

//val credentials = rootProject.file("local.properties")
//val credentialProperty = java.util.Properties().apply {
//    load(credentials.inputStream())
//}

android {
    namespace = "com.joel.buster"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.joel.buster"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

//        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
//        buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
//        buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
//        buildConfigField("String", "ORIGINAL_IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")


//        val properties = java.util.Properties().apply {
//            load(project.rootProject.file("local.properties").inputStream())
//        }
//        buildConfigField("String", "TMDB_API_KEY", "\"${properties.getProperty("TMDB_API_KEY")}\"")

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:discover"))
    implementation(project(":feature:watchlist"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:search"))
    implementation(project(":feature:media"))
    implementation(project(":core:design"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation)
    implementation (libs.androidx.core.ktx)
    implementation (libs.bundles.lifecycle)
    implementation (libs.androidx.activity.compose)
    implementation (platform(libs.androidx.compose.bom))
    implementation (libs.bundles.compose)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)
    testImplementation (libs.junit)
    androidTestImplementation (libs.bundles.junit.test)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (platform(libs.androidx.compose.bom))
    debugImplementation (libs.bundles.compose.debug)

}