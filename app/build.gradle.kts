plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

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
    implementation (libs.androidx.core.ktx)
    implementation (libs.bundles.lifecycle)
    implementation (libs.androidx.activity.compose)
    implementation (platform(libs.androidx.compose.bom))
    implementation (libs.bundles.compose)
    testImplementation (libs.junit)
    androidTestImplementation (libs.bundles.junit.test)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (platform(libs.androidx.compose.bom))
    debugImplementation (libs.bundles.compose.debug)
}