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
        gradlePluginPortal()
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}

rootProject.name = "FM_Hub"
include(":app")
include(":fm_hub_uijung")
include(":fm_hub_dazzang2")
include(":fm_hub_yeonsinkeem2")
