plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.joel.design"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

dependencies {

    implementation (libs.bundles.lifecycle)
    implementation (libs.androidx.activity.compose)
    implementation (platform(libs.androidx.compose.bom))
    implementation (libs.bundles.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompact)
    implementation(libs.androidx.material)
    implementation(libs.androidx.content.alpha)
    testImplementation(libs.junit)
    androidTestImplementation (libs.bundles.junit.test)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation (platform(libs.androidx.compose.bom))
    debugImplementation (libs.bundles.compose.debug)
}