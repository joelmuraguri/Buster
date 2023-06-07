pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Buster"
include (":app")
include(":feature:discover")
include(":feature:media")
include(":feature:watchlist")
include(":feature:profile")
include(":feature:search")
include(":core:design")
include(":core:network")
include(":core:common")
