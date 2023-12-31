@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.joel.build-logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin{
    plugins {
        create("kotlinAndroid"){
            id = "buster.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }

        create("androidCompose"){
            id = "buster.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        create("androidApplication"){
            id = "buster.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        create("androidLibrary"){
            id = "buster.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        create("androidHilt"){
            id = "buster.android.hilt"
            implementationClass = "HiltAndroidConventionPlugin"
        }

        create("androidKtorClient"){
            id = "buster.ktor.client"
            implementationClass = "KtorClientConventionPlugin"
        }

        create("androidRoom"){
            id = "buster.room.android"
            implementationClass = "RoomAndroidConventionPlugin"
        }

        create("sharedLibrary"){
            id = "buster.shared.library"
            implementationClass = "SharedLibraryConventionPlugin"
        }

    }
}