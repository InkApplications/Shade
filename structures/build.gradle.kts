plugins {
    id("library")
    kotlin("plugin.serialization")
    id("com.inkapplications.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlinLibraries.serialization.json)
                implementation(projects.serialization)
                api(kotlinLibraries.coroutines.core)
                api(kotlinLibraries.datetime)
                api(inkLibraries.spondee)
            }
        }
    }
}
