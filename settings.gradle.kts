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

rootProject.name = "Bubble"
include(":app")
include(":feature:home")
include(":feature:water")
include(":feature:award")
include(":feature:history")
include(":data")
include(":domain")
include(":core")
include(":feature:statistics")
include(":feature:settings")
include(":feature:relax")
