pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "RadioStations"
include(":app")
include(":app:presentation")
include(":app:domain")
include(":app:data")
include(":core:commons:utils:impl")
include(":core:commons:utils:api")
include(":core:design-system")
include(":core:test")
include(":core:commons:presentation")
