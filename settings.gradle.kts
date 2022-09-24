pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://jitpack.io")

        // Register the AndroidX snapshot repository first so snapshots don't attempt (and fail)
        // to download from the non-snapshot repositories
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            content {
                // The AndroidX snapshot repository will only have androidx artifacts, don't
                // bother trying to find other ones
                includeGroupByRegex("androidx\\..*")
            }
        }
        google()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "WanAndroid-Architecture"
include(":app")
include(":appmodule")
include(":appmodule:my")
include(":common")
include(":common:core-network")
include(":common:core-lib")
include(":common:core-utils")
include(":appmodule:home")
include(":appmodule:navigation")
