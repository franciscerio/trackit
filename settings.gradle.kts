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
include(":app")
include(":core")
include(":features")
include(":core:domain")
include(":core:network")
include(":features:tracks")
include(":core:local")
include(":features:tracks:network")
include(":features:tracks:data")
include(":features:tracks:domain")
include(":features:tracks:ui")
