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
    versionCatalogs {
        create("testLibs") {
            from(files("testing-dependencies.toml"))
        }
    }
}

rootProject.name = "Trackit"
// This will enable the gradle dependency naming conventions to simple and prevented hardcoded module names
// Example: implementation(":core:common") to implementation(projects.core.common)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

// Core Dependencies
include(":core")
include(":core:common")
include(":core:common-compose")
include(":core:common-android")
include(":core:domain")
include(":core:local")
include(":core:network")

// Features Dependencies
include(":features")
include(":features:tracks")
include(":features:tracks:network")
include(":features:tracks:data")
include(":features:tracks:ui")
include(":features:tracks:domain")

